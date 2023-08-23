package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.Task;

import javax.validation.constraints.NotNull;

@Data
public class TaskTagDTO {
    @NotNull
    private String name;
    @NotNull
    private String color;
    @NotNull
    private Long workspaceId;
    @NotNull
    private Long taskId;
}
