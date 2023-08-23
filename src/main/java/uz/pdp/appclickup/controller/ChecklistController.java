package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.ChecklistDTO;
import uz.pdp.appclickup.service.ChecklistService;

@RestController
@RequestMapping("/api/checklist")
public class ChecklistController {
    @Autowired
    ChecklistService checklistService;

    @PostMapping("/addChecklistToTask")
    public HttpEntity<?> addChecklistToTask(ChecklistDTO checklistDTO) {
        ApiResponse apiResponse = checklistService.addChecklistToTask(checklistDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

}
