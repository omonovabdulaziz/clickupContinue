package uz.pdp.appclickup.service;

import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.ChecklistDTO;

import java.util.UUID;

public interface ChecklistService {
    ApiResponse addChecklistToTask(ChecklistDTO checklistDTO);

    ApiResponse updateCheckListToTask(Long CheckListId, ChecklistDTO checklistDTO);

    ApiResponse deleteChecklistFromTask(Long ChecklistId);

    ApiResponse assignUserToChecklist(Long checkListId, UUID userId);

}
