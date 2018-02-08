package cz.cvut.fel.jee.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Vaclav Rechtberger
 */
@Data
@Entity
@Table(name="addresses")
public class Address implements Serializable, Identifiable {
    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String houseNumber;

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String code;

    @NotEmpty
    private String country;

    public Address(String houseNumber, String street, String city, String code, String country) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.code = code;
        this.country = country;
    }

    public Address() {
    }

}
