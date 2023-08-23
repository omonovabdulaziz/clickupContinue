package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickup.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Long> {
}
