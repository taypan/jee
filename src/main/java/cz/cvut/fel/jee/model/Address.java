package cz.cvut.fel.jee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Vaclav Rechtberger
 */
@Entity
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String houseNumber;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String code;

    @NotNull
    private String country;
}
