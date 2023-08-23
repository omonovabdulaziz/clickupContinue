package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickup.entity.CheckList;

public interface ChecklistRepository extends JpaRepository<CheckList , Long> {
    boolean existsByNameAndTaskId(String name, Long task_id);
}
