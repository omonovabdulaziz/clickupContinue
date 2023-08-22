package uz.pdp.appclickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspaceDTO {
    private Long id;
    @NotNull
    private String name;

    @NotNull
    private String color;

    private UUID avatarId;
    private UUID ownerId;
    private String initialLetter;


}
