package uz.pdp.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.entity.*;
import uz.pdp.appclickup.entity.enums.AddType;
import uz.pdp.appclickup.entity.enums.WorkspacePermissionName;
import uz.pdp.appclickup.entity.enums.WorkspaceRoleName;
import uz.pdp.appclickup.payload.*;
import uz.pdp.appclickup.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;
    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user) {
        //WORKSPACE OCHDIK
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName()))
            return new ApiResponse("Sizda bunday nomli ishxona mavjud", false);
        Workspace workspace = new Workspace(
                workspaceDTO.getName(),
                workspaceDTO.getColor(),
                user,
                workspaceDTO.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("attachment"))
        );
        workspaceRepository.save(workspace);

        //WORKSPACE ROLE OCHDIK
        WorkspaceRole ownerRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_OWNER.name(),
                null
        ));
        WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_ADMIN.name(), null));
        WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_MEMBER.name(), null));
        WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_GUEST.name(), null));


        //OWERGA HUQUQLARNI BERYAPAMIZ
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissions = new ArrayList<>();

        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole,
                    workspacePermissionName);
            workspacePermissions.add(workspacePermission);
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissions.add(new WorkspacePermission(
                        adminRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissions.add(new WorkspacePermission(
                        memberRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissions.add(new WorkspacePermission(
                        guestRole,
                        workspacePermissionName));
            }

        }
        workspacePermissionRepository.saveAll(workspacePermissions);

        //WORKSPACE USER OCHDIK
        workspaceUserRepository.save(new WorkspaceUser(
                workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())

        ));

        return new ApiResponse("Ishxona saqlandi", true);
    }

    @Override
    public ApiResponse editWorkspace(WorkspaceDTO workspaceDTO) {
        Workspace workspaceEdit = workspaceRepository.findByName(workspaceDTO.getName());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(workspaceDTO.getAvatarId());
        if (workspaceRepository.existsByOwnerIdAndName(workspaceEdit.getOwner().getId(), workspaceDTO.getName()))
            return new ApiResponse("sizda ushbu nomdagi workspace mavjud", false);
        workspaceEdit.setAvatar(optionalAttachment.get());
        workspaceEdit.setName(workspaceDTO.getName());
        workspaceEdit.setColor(workspaceDTO.getColor());
        workspaceRepository.save(workspaceEdit);
        return new ApiResponse("Barcha malumotlar o'zgartirildi", true);
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, UUID ownerId) {
        Optional<User> optionalUser = userRepository.findById(ownerId);
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(id);
        if (!optionalUser.isPresent() && !optionalWorkspace.isPresent())
            return new ApiResponse("workspace yoki userTopilmadi", false);

        Workspace workspace = optionalWorkspace.get();
        User user = optionalUser.get();
        workspace.setOwner(user);
        workspaceRepository.save(workspace);
        return new ApiResponse("ownerO'zgartirildi", true);

    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {
        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik", false);
        }
    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO) {
        if (memberDTO.getAddType().equals(AddType.ADD)) {
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    userRepository.findById(memberDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workspaceUserRepository.save(workspaceUser);

            //TODO EMAILGA INVITE XABAR YUBORISH
        } else if (memberDTO.getAddType().equals(AddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDTO.getAddType().equals(AddType.REMOVE)) {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, memberDTO.getId());
        }
        return new ApiResponse("Muvaffaqiyatli", true);
    }

    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }

    @Override
    public Page<WorkspaceUser> getMemberAndMehmon(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return workspaceUserRepository.findByWorkspaceId(id, pageable);
    }

    @Override
    public List<Workspace> getWorkspaceList(UUID userid) {
        return workspaceRepository.findByOwnerId(userid);
    }
    //role qoshish va permission qoshish

    @Override
    public ApiResponse addRoleToWorkpace(WorkspaceRoleDTO workspaceRoleDTO) {
        if (workspaceRoleRepository.existsByWorkspaceNameAndWorkspaceId(workspaceRoleDTO.getName(), workspaceRoleDTO.getWorkspaceId()))
            return new ApiResponse("Bunday nomli role ushbu Workspacega qo'shilgan", false);
        WorkspaceRole workspaceRole = new WorkspaceRole();
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceRoleDTO.getWorkspaceId());
        workspaceRole.setWorkspace(optionalWorkspace.get());
        workspaceRole.setName(workspaceRoleDTO.getName());
        workspaceRole.setExtendsRole(workspaceRoleDTO.getExtendsRole());
        workspaceRoleRepository.save(workspaceRole);
        return new ApiResponse("Yangi Role Ushbu Workspacega Qo'shildi", true);
    }

    @Override
    public ApiResponse addWorkspacePermission(WorkspacePermissionDTO workspacePermissionDTO) {
        Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(workspacePermissionDTO.getWorkspaceRoleId());
        WorkspacePermission permission = new WorkspacePermission();
        permission.setWorkspaceRole(optionalWorkspaceRole.get());
        permission.setPermission(workspacePermissionDTO.getWorkspacePermission().getPermission());
        workspacePermissionRepository.save(permission);
        return new ApiResponse("Ushbu permission ushbu workspacedagi role uchun saqlandi", true);
    }


}
