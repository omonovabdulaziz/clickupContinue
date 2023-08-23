package uz.pdp.appclickup.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appclickup.entity.Attachment;
import uz.pdp.appclickup.entity.Task;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.repository.AttachmentContentRepository;
import uz.pdp.appclickup.repository.AttachmentRepository;
import uz.pdp.appclickup.repository.TaskRepository;
import uz.pdp.appclickup.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service

public class AttachmentTaskImpl implements AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    TaskRepository taskRepository;
    private static final String uploadDirectory = "yuklanganlar";

    @Override
    public ApiResponse addFileToTask(Long taskId, MultipartHttpServletRequest request) throws IOException {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (!optionalTask.isPresent())
            return new ApiResponse("Task topilmadi", false);

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String originalName = file.getOriginalFilename();
            Attachment attachment = new Attachment();
            attachment.setOriginalName(originalName);
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            String[] split = originalName.split("\\.");
            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
            attachment.setName(name);
            Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);
            Task task = optionalTask.get();
            task.setAttachment(attachment);
            Task save = taskRepository.save(task);
            return new ApiResponse("Fayl saqlandi va ushbu taskga biriktirildi", true);
        }
        return new ApiResponse("Malumotlar saqlnamadi fayl tashlanmadi", false);
    }

    @Override
    public ApiResponse deleteFileFromTask(Long taskId) {
        return null;
    }



    @Override
    public Attachment getAttachmentById(UUID id, HttpServletResponse httpServletResponse) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename= \"" + attachment.getOriginalName());
            httpServletResponse.setContentType(attachment.getContentType());
            FileInputStream fileInputStream = new FileInputStream(uploadDirectory + "/" + attachment.getName());
            FileCopyUtils.copy(fileInputStream, httpServletResponse.getOutputStream());
        }
        return null;
    }
}
