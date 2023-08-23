package uz.pdp.appclickup.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appclickup.entity.Attachment;
import uz.pdp.appclickup.payload.ApiResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public interface AttachmentService {
    ApiResponse addFileToTask(Long taskId, MultipartHttpServletRequest request) throws IOException;

    ApiResponse deleteFileFromTask(Long taskId);



    Attachment getAttachmentById(UUID id, HttpServletResponse httpServletResponse) throws IOException;
}
