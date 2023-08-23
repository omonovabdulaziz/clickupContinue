package uz.pdp.appclickup.service;

import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.ChecklistDTO;

public interface ChecklistService {
ApiResponse addChecklistToTask(ChecklistDTO checklistDTO);
}
