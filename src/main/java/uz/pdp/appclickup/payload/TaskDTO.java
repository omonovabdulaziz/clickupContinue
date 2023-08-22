package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.enums.PriorityType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Data

public class TaskDTO {
    @NotNull
    private String name;
    @NotNull
    private String description;
    private Long statusId;
    @Enumerated(EnumType.STRING)
    private PriorityType priorityType;
    private Date dueDate;
    private Timestamp dueTimeHas;
}
