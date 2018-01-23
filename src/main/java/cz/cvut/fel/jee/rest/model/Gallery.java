package cz.cvut.fel.jee.rest.model;

import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
