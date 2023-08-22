package uz.pdp.appclickup.service;

import org.springframework.data.domain.Page;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.entity.Workspace;
import uz.pdp.appclickup.entity.WorkspaceUser;
import uz.pdp.appclickup.payload.*;

import java.util.List;
import java.util.UUID;


public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse editWorkspace(WorkspaceDTO workspaceDTO);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO);

    ApiResponse joinToWorkspace(Long id, User user);

    List<MemberDTO> getMemberAndMehmon(Long id);

    List<WorkspaceDTO> getMyWorkspace(User user);

    ApiResponse addWorkspacePermission(WorkspacePermissionDTO workspacePermissionDTO);

    ApiResponse addOrRemovePermissionToRole(WorkspaceRoleDTO workspaceRoleDTO);

}
