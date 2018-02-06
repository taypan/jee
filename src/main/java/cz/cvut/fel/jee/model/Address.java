package cz.cvut.fel.jee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Vaclav Rechtberger
 */
@Entity
@Table(name="addresses")
public class Address implements Serializable, Identifiable {
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

    public Address(String houseNumber, String street, String city, String code, String country) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.code = code;
        this.country = country;
    }

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
