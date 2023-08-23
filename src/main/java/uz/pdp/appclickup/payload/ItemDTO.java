package uz.pdp.appclickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemDTO {
    @NotNull
    private String name;
    private Long checkListId;
}
