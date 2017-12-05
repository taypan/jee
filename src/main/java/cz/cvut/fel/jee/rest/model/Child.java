package cz.cvut.fel.jee.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Vaclav Rechtberger
 */
@Entity
public class Child {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    public Child(String name) {
        this.name = name;
    }

    public Child() {
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
}
