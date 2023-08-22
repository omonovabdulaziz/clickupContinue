package uz.pdp.appclickup.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.entity.Status;
import uz.pdp.appclickup.entity.Task;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.entity.enums.AssignType;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.AssignUserDTO;
import uz.pdp.appclickup.payload.TaskDTO;
import uz.pdp.appclickup.repository.StatusRepository;
import uz.pdp.appclickup.repository.TaskRepository;
import uz.pdp.appclickup.repository.UserRepository;
import uz.pdp.appclickup.service.TaskService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescripton(taskDTO.getDescription());
        task.setName(taskDTO.getName());
        Optional<Status> optionalStatus = statusRepository.findById(taskDTO.getStatusId());
        task.setStatus(optionalStatus.get());
        task.setDueDate((Date) taskDTO.getDueDate());
        task.setDueTimeHas(taskDTO.getDueTimeHas());
        task.setPriorityType(taskDTO.getPriorityType());
        taskRepository.save(task);
        return new ApiResponse("task qo'shildi", true);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public ApiResponse updateTaskById(TaskDTO taskDTO, Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday task mavjud emas", false);

        Task task = optionalTask.get();
        task.setName(taskDTO.getName());
        task.setDescripton(taskDTO.getDescription());
        Optional<Status> optionalStatus = statusRepository.findById(taskDTO.getStatusId());
        task.setStatus(optionalStatus.get());
        task.setPriorityType(taskDTO.getPriorityType());
        task.setDueTimeHas(taskDTO.getDueTimeHas());
        task.setDueDate((Date) taskDTO.getDueDate());
        taskRepository.save(task);
        return new ApiResponse("task yangilandi", true);
    }

    @Override
    public ApiResponse deleteTask(Long id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("!Xatolikk", false);
        }
    }

    @Override
    public ApiResponse assignOrDisAssignUserToTask(AssignUserDTO assignUserDTO) {
        AssignType assignType = assignUserDTO.getAssignType();
        Optional<User> optionalUser = userRepository.findById(assignUserDTO.getUserId());
        if (!optionalUser.isPresent()) return new ApiResponse("Bunday id li user mavjud emas", false);
        Optional<Task> optionalTask = taskRepository.findById(assignUserDTO.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Ushbu task Topilmadi", false);
        Task task = optionalTask.get();
        if (assignType.equals(AssignType.ASSIGN_ABLE)) {
            task.setAssignUser(optionalUser.get());
            taskRepository.save(task);
            return new ApiResponse("User tasdiqlandi ushbu task uchun", true);
        } else if (assignType.equals(AssignType.ASSIGN_DISABLE)) {
            task.setAssignUser(null);
            taskRepository.save(task);
            return new ApiResponse("User o'chirib tashlandi ushbu task uchun ", false);
        }
        return new ApiResponse("Bunday assignType mavjud emas", false);
    }
}
