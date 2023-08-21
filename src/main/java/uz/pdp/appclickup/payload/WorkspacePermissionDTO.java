package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.WorkspacePermission;
import uz.pdp.appclickup.entity.enums.WorkspaceRoleName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspacePermissionDTO {
    @NotNull
    private UUID workspaceRoleId;
    @Enumerated(EnumType.STRING)
    private WorkspacePermission workspacePermission;
}
