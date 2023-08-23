package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.ChecklistDTO;

import uz.pdp.appclickup.service.ChecklistService;

import java.util.UUID;

@RestController
@RequestMapping("/api/checklist")
public class ChecklistController {
    @Autowired
    ChecklistService checklistService;

    @PostMapping("/addChecklistToTask")
    public HttpEntity<?> addChecklistToTask(@RequestBody ChecklistDTO checklistDTO) {
        ApiResponse apiResponse = checklistService.addChecklistToTask(checklistDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/updateCheckListToTask/{CheckListId}")
    public HttpEntity<?> updateCheckListToTask(@PathVariable Long CheckListId, @RequestBody ChecklistDTO checklistDTO) {
        ApiResponse apiResponse = checklistService.updateCheckListToTask(CheckListId, checklistDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/deleteChecklistFromTask/{ChecklistId}")
    public HttpEntity<?> deleteChecklistFromTask(@PathVariable Long ChecklistId) {
        ApiResponse apiResponse = checklistService.deleteChecklistFromTask(ChecklistId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/ assignUserToChecklist/{checkListId}/{userId}")
    public HttpEntity<?> assignUserToChecklist(@PathVariable Long checkListId, @PathVariable UUID userId) {
        ApiResponse apiResponse = checklistService.assignUserToChecklist(checkListId, userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
