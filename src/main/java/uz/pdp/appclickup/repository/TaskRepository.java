package uz.pdp.appclickup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickup.entity.Task;

public interface TaskRepository extends JpaRepository<Task , Long> {
}
