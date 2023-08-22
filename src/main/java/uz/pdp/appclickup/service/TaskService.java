package uz.pdp.appclickup.service;

import uz.pdp.appclickup.entity.Task;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.AssignUserDTO;
import uz.pdp.appclickup.payload.TaskDTO;

import java.util.List;

public interface TaskService {
    ApiResponse addTask(TaskDTO taskDTO);

    List<Task> getAllTask();

    ApiResponse updateTaskById(TaskDTO taskDTO, Long id);

    ApiResponse deleteTask(Long id);
    ApiResponse assignOrDisAssignUserToTask(AssignUserDTO assignUserDTO);
}
