package uz.pdp.appclickup.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.entity.Attachment;
import uz.pdp.appclickup.entity.Space;
import uz.pdp.appclickup.entity.Workspace;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.SpaceDTO;
import uz.pdp.appclickup.repository.AttachmentRepository;
import uz.pdp.appclickup.repository.SpaceRepository;
import uz.pdp.appclickup.repository.WorkspaceRepository;
import uz.pdp.appclickup.service.SpaceService;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceServiceImpl implements SpaceService {
    @Autowired
    SpaceRepository spaceRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    WorkspaceRepository workspaceRepository;

    @Override
    public ApiResponse addSpace(SpaceDTO spaceServiceDTO) {

        if (spaceRepository.findSpaceByNameAndWorkspaceId(spaceServiceDTO.getName(), spaceServiceDTO.getWorkspaceId()).isPresent())
            return new ApiResponse("Ushbu nomli service ushbu workspacega qo'shilgan boshqa nomdan foydalanib koring", false);


        Optional<Attachment> optionalAttachment = attachmentRepository.findById(spaceServiceDTO.getAttachmentId());
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(spaceServiceDTO.getWorkspaceId());
        Space space = new Space();
        space.setAttachment(optionalAttachment.get());
        space.setName(spaceServiceDTO.getName());
        space.setWorkspace(optionalWorkspace.get());
        space.setColor(spaceServiceDTO.getColor());
        spaceRepository.save(space);
        return new ApiResponse("Ushbu space ushbu workspacega qo'shildi", true);
    }

    @Override
    public List<Space> getSpace() {
        return spaceRepository.findAll();
    }

    @Override
    public ApiResponse updateSpace(Long id, SpaceDTO spaceDTO) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (!optionalSpace.isPresent())
            return new ApiResponse("BUnday id li workspace topilmadi", false);
        Space space = optionalSpace.get();
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(spaceDTO.getAttachmentId());
        space.setAttachment(optionalAttachment.get());
        space.setName(spaceDTO.getName());
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(spaceDTO.getWorkspaceId());
        space.setWorkspace(optionalWorkspace.get());
        space.setColor(spaceDTO.getColor());
        spaceRepository.save(space);
        return new ApiResponse("Barcha malumotlaringiz yangilandi", true);
    }

    @Override
    public ApiResponse deleteSpace(Long id) {
        try {
            spaceRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);

        } catch (Exception e) {
            return new ApiResponse("Xatolik o'chirilmadi", false);
        }

    }

    @Override
    public Space getSpaceById(Long id) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        return optionalSpace.orElse(null);

    }
}
