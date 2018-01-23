package cz.cvut.fel.jee.model;

import com.fasterxml.jackson.annotation.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Vaclav Rechtberger
 */
@Entity
public class Parent implements Serializable{
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    //@JacksonInject
    //@Transient
    @OneToOne
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class ,property="id",scope = Parent.class)
    @JsonIdentityReference(alwaysAsId=true)
    private Child child;

    public Parent(String name) {
        this.name = name;
    }
///*
    public Parent(String name, Child child) {
        this.name = name;
        this.child = child;
    }

    @JsonCreator
    public Parent(@JsonProperty("name") String name, @JsonProperty("child") long child) throws NamingException {
        this.name = name;
        EntityManager entityManager = InitialContext.doLookup("java:/defaultEntityManager");
        this.child = entityManager.find(Child.class,child);
    }

    public Parent() {
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

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
/*
    @JsonProperty("child")
    public void setChild(long child) {
        System.out.println("child: " + child);

        this.setChild(findById(child));
        //this.setChild(entityManager.find(Child.class,child));
    }
*//*
    @JsonCreator
    public static Parent findById(@JacksonInject EntityManager entityManager,long id) {
        return entityManager.find(Parent.class, id);
    }*/
}
