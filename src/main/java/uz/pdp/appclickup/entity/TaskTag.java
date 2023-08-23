package uz.pdp.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.template.AbsLongEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskTag extends AbsLongEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String color;
    @ManyToOne
    private Workspace workspace;
    @ManyToOne
    private Task task;
}
