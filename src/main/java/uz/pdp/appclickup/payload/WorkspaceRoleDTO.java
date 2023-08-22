package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.WorkspacePermission;
import uz.pdp.appclickup.entity.enums.AddType;
import uz.pdp.appclickup.entity.enums.WorkspaceRoleName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspaceRoleDTO {
    private UUID id;
    private String name;
    private WorkspaceRoleName extendsRole;
    private WorkspacePermission permissionName;
    private AddType addType;
}
