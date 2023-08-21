package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickup.entity.Space;

import java.util.Optional;

public interface SpaceRepository extends JpaRepository<Space , Long> {
    Optional<Space> findSpaceByNameAndWorkspaceId(String name, Long workspace_id);
}
