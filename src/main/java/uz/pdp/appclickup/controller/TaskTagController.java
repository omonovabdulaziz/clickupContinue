package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickup.entity.TaskTag;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.TaskTagDTO;
import uz.pdp.appclickup.service.TaskTagService;

import java.awt.image.ReplicateScaleFilter;
import java.util.List;

@RestController
@RequestMapping("/api/taskTag")
public class TaskTagController {
    @Autowired
    TaskTagService taskTagService;

    @PostMapping("/taskTag")
    public ResponseEntity<?> addTagToTask(@RequestBody TaskTagDTO taskTagDTO) {
        ApiResponse apiResponse = taskTagService.addTagToTask(taskTagDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/deleteTag/{tagId}")
    public ResponseEntity<?> deleteTagFromTask(@PathVariable Long taskId) {
        ApiResponse apiResponse = taskTagService.deleteTagFromTask(taskId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/updateTag/{tagId}")
    public ResponseEntity<?> updateTagTask(@PathVariable Long tagId, @RequestBody TaskTagDTO taskTagDTO) {
        ApiResponse apiResponse = taskTagService.updateTagTask(tagId, taskTagDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/getTagByTask/{taskId}")
    public List<TaskTag> getTagByTaskId(@PathVariable Long taskId) {
        return taskTagService.getTagByTaskId(taskId);
    }


}
