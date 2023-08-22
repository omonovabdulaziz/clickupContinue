package uz.pdp.appclickup.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import uz.pdp.appclickup.entity.enums.PriorityType;
import uz.pdp.appclickup.entity.template.AbsLongEntity;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task extends AbsLongEntity {
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "text")
    private String descripton;
    @ManyToOne
    private Status status;
    @Enumerated(EnumType.STRING)
    private PriorityType priorityType;
    @CreatedDate
    private Date startDate;
    @CreationTimestamp
    private Timestamp startTimeHas;
    private Date dueDate;
    private Timestamp dueTimeHas;


}
