package uz.pdp.appclickup.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.entity.Task;
import uz.pdp.appclickup.entity.TaskTag;
import uz.pdp.appclickup.entity.Workspace;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.TaskTagDTO;
import uz.pdp.appclickup.repository.TaskRepository;
import uz.pdp.appclickup.repository.TaskTagRepository;
import uz.pdp.appclickup.repository.WorkspaceRepository;
import uz.pdp.appclickup.service.TaskTagService;

import java.util.List;
import java.util.Optional;

@Service
public class TaskTagServiceImpl implements TaskTagService {
    @Autowired
    TaskTagRepository taskTagRepository;
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    TaskRepository taskRepository;

    @Override
    public ApiResponse addTagToTask(TaskTagDTO taskTagDTO) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(taskTagDTO.getWorkspaceId());
        if (!optionalWorkspace.isPresent())
            return new ApiResponse("Bunday workspace mavjud emas ", false);
        Optional<Task> optionalTask = taskRepository.findById(taskTagDTO.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Topilmadi", false);
        TaskTag taskTag = new TaskTag(taskTagDTO.getName(), taskTagDTO.getColor(), optionalWorkspace.get(), optionalTask.get());
        taskTagRepository.save(taskTag);
        return new ApiResponse("Ushbu taskTag ushbu taskka saqlandi", true);
    }

    @Override
    public ApiResponse deleteTagFromTask(Long TagId) {
        try {
            taskTagRepository.deleteById(TagId);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("!Xatolik", false);
        }
    }

    @Override
    public ApiResponse updateTagTask(Long tagId, TaskTagDTO taskTagDTO) {
        Optional<TaskTag> optionalTaskTag = taskTagRepository.findById(tagId);
        if (!optionalTaskTag.isPresent())
            return new ApiResponse("Bunday tag topilmadi", false);
        TaskTag taskTag = optionalTaskTag.get();
        taskTag.setColor(taskTagDTO.getColor());
        taskTag.setName(taskTagDTO.getName());
        Optional<Task> optionalTask = taskRepository.findById(taskTagDTO.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Topilmadi", false);
        taskTag.setTask(optionalTask.get());
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(taskTagDTO.getWorkspaceId());
        if (!optionalWorkspace.isPresent())
            return new ApiResponse("Bunday workspace topilmadi", false);

        taskTag.setWorkspace(optionalWorkspace.get());
        taskTagRepository.save(taskTag);
        return new ApiResponse("O'zgartirildi", true);
    }

    @Override
    public List<TaskTag> getTagByTaskId(Long taskId) {
        return taskTagRepository.findAllByTaskId(taskId);
    }
}
