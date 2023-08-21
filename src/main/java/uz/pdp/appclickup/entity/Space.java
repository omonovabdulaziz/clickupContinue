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
public class Space extends AbsLongEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String initialLetter;
    @ManyToOne
    private Attachment attachment;
    @ManyToOne
    private Workspace workspace;

    public void setInitialLetter() {
        this.initialLetter = name.substring(0, 1);
    }

    public Space(String name, String color, Attachment attachment, Workspace workspace) {
        this.name = name;
        this.color = color;
        this.attachment = attachment;
        this.workspace = workspace;
    }
}
