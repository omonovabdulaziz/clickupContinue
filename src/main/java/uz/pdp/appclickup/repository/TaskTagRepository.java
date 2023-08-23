package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickup.entity.TaskTag;

import java.util.List;

public interface TaskTagRepository extends JpaRepository<TaskTag , Long> {
List<TaskTag> findAllByTaskId(Long task_id);
}
