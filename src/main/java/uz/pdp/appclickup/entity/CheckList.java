package uz.pdp.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.template.AbsLongEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "owner_id"})})
public class CheckList extends AbsLongEntity {
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Task task;
    @ManyToOne
    private User assignUser;

    public CheckList(String name, Task task) {
        this.name = name;
        this.task = task;
    }
}
