package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.enums.WorkspaceRoleName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
public class WorkspaceRoleDTO {
    @NotNull
    private Long workspaceId;
    @NotNull
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private WorkspaceRoleName extendsRole;
}
