package cz.cvut.fel.jee.model;

import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="galleries")
public class Gallery {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    @NotNull
    @Column(columnDefinition="TEXT")
    private String base64;

    public Gallery() {
    }

}
