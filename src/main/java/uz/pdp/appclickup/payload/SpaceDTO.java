package uz.pdp.appclickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class SpaceDTO {
    @NotNull
    private String name;
    @NotNull
    private String color;
    @NotNull
    private String initialLetter;
    private UUID attachmentId;
    private Long workspaceId;

}
