package uz.pdp.appclickup.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data

public class ChecklistDTO {
    private String name;
    private Long TaskId;
    private UUID assignedUserId;
}
