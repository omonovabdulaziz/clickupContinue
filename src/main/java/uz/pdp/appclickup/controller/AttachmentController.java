package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appclickup.entity.Attachment;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;


    @PostMapping("/addFileToTask/{taskId}")
    public HttpEntity<?> addFileToTask(@PathVariable Long taskId, MultipartHttpServletRequest request) throws IOException {
        ApiResponse apiResponse = attachmentService.addFileToTask(taskId, request);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/deleteFileFromTask/{taskId}")
    public HttpEntity<?> deleteFileFromTask(@PathVariable Long taskId) {
        ApiResponse apiResponse = attachmentService.deleteFileFromTask(taskId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }



    @GetMapping("/getAttachmentById/{id}")
    public Attachment getAttachmentById(@PathVariable UUID id, HttpServletResponse httpServletResponse) throws IOException {
        return attachmentService.getAttachmentById(id, httpServletResponse);
    }

}
