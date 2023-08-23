package uz.pdp.appclickup.service;

import uz.pdp.appclickup.entity.TaskTag;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.TaskTagDTO;

import java.util.List;

public interface TaskTagService {

    ApiResponse addTagToTask(TaskTagDTO taskTagDTO);
    ApiResponse deleteTagFromTask(Long taskId);
    ApiResponse updateTagTask(Long tagId , TaskTagDTO taskTagDTO);
    List<TaskTag> getTagByTaskId(Long taskId);
}
