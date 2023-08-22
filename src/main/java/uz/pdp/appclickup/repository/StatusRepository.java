package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickup.entity.Status;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status , Long> {
boolean existsByNameAndSpaceId(String name, Long space_id);
List<Status> findAllBySpaceId(Long space_id);
}
