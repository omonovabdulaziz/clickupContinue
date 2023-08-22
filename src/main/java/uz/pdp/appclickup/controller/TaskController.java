package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickup.entity.Task;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.AssignUserDTO;
import uz.pdp.appclickup.payload.TaskDTO;
import uz.pdp.appclickup.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO) {
        ApiResponse apiResponse = taskService.addTask(taskDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/getAllTask")
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<?> updateTaskById(@RequestBody TaskDTO taskDTO, @PathVariable Long id) {
        ApiResponse apiResponse = taskService.updateTaskById(taskDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        ApiResponse apiResponse = taskService.deleteTask(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/assignOrDisAssignUser")
    public ResponseEntity<?> assignOrDisAssignUserToTask(@RequestBody AssignUserDTO assignUserDTO) {
        ApiResponse apiResponse = taskService.assignOrDisAssignUserToTask(assignUserDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


}
