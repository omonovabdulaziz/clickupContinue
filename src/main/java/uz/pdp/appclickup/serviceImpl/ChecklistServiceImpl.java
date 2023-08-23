package uz.pdp.appclickup.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appclickup.entity.CheckList;
import uz.pdp.appclickup.entity.Task;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.ChecklistDTO;
import uz.pdp.appclickup.repository.ChecklistRepository;
import uz.pdp.appclickup.repository.TaskRepository;
import uz.pdp.appclickup.repository.UserRepository;
import uz.pdp.appclickup.service.ChecklistService;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChecklistServiceImpl implements ChecklistService {
    @Autowired
    ChecklistRepository checklistRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse addChecklistToTask(ChecklistDTO checklistDTO) {
        if (checklistRepository.existsByNameAndTaskId(checklistDTO.getName(), checklistDTO.getTaskId()))
            return new ApiResponse("Bu taskga bunday nomli Checklist qo'shilgan", false);

        Optional<Task> optionalTask = taskRepository.findById(checklistDTO.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday id li task topilmadi", false);
        CheckList checkList = new CheckList(checklistDTO.getName(), optionalTask.get());
        checklistRepository.save(checkList);
        return new ApiResponse("Saqlandi", true);

    }

    @Override
    public ApiResponse updateCheckListToTask(Long CheckListId, ChecklistDTO checklistDTO) {
        Optional<CheckList> optionalCheckList = checklistRepository.findById(CheckListId);
        if (!optionalCheckList.isPresent())
            return new ApiResponse("Bunday Cheklist mavjud", false);
        CheckList checkList = optionalCheckList.get();
        if (checklistRepository.existsByNameAndTaskId(checklistDTO.getName(), checklistDTO.getTaskId()))
            return new ApiResponse("Bu taskga bunday nomli Checklist qo'shilgan", false);
        checkList.setTask(taskRepository.findById(checklistDTO.getTaskId()).get());
        checkList.setName(checklistDTO.getName());
        checklistRepository.save(checkList);
        return new ApiResponse("O'zgartirildi", true);
    }

    @Override
    public ApiResponse deleteChecklistFromTask(Long ChecklistId) {
        try {
            checklistRepository.deleteById(ChecklistId);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("!Xatolik", false);
        }
    }

    @Override
    public ApiResponse assignUserToChecklist(Long checkListId, UUID userId) {
        Optional<CheckList> optionalCheckList = checklistRepository.findById(checkListId);
        if (!optionalCheckList.isPresent())
            return new ApiResponse("Budnay id li cheklist topilmadi", false);

        CheckList checkList = optionalCheckList.get();
        Optional<User> optionalUser = userRepository.findById(userId);
        checkList.setAssignUser(optionalUser.get());
    checklistRepository.save(checkList);
    return new ApiResponse("User tasdiqlandi" , true);
    }
}
