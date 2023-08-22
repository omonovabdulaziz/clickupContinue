package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.entity.enums.AssignType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class AssignUserDTO {
    @NotNull
    private UUID userId;
    @Enumerated(EnumType.STRING)
    @NotNull
    private AssignType assignType;
}
