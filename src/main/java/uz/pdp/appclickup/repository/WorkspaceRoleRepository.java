package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickup.entity.Workspace;
import uz.pdp.appclickup.entity.WorkspaceRole;

import java.util.Optional;
import java.util.UUID;

public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {
boolean existsByWorkspaceNameAndWorkspaceId(String workspace_name, Long workspace_id);
}
