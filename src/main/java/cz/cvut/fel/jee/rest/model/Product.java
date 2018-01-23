package cz.cvut.fel.jee.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Vaclav Rechtberger
 */
@Entity
@Table(name="products")
public class Product implements Serializable{

    @Id @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 2, max = 25)
    @Pattern(regexp = "[a-zA-Z0-9ěščřžýáíéúůďťó]", message = "Must contains only alfanumeric symbols and \"-\", \":\", \",\" or <space>")
    private String name;
    @NotNull
    @Size(min = 2, max = 25)
    @Pattern(regexp = "[a-zA-Z0-9ěščřžýáíéúůďťó]", message = "Must contains only alfanumeric symbols and \"-\", \":\", \",\" or <space>")
    private String description;
    @NotNull
    @Size(min = 2, max = 25)
    @Pattern(regexp = "[a-zA-Z0-9ěščřžýáíéúůďťó]", message = "Must contains only alfanumeric symbols and \"-\", \":\", \",\" or <space>")
    private String manufacturer;                //ID ref
    @NotNull
    @Size(min = 2, max = 25)
    @Pattern(regexp = "[a-zA-Z0-9ěščřžýáíéúůďťó]", message = "Must contains only alfanumeric symbols and \"-\", \":\", \",\" or <space>")
    private String model;                       //manufacturer model id
    @NotNull
    @Size(min = 2, max = 25)
    @Pattern(regexp = "[a-zA-Z0-9ěščřžýáíéúůďťó]", message = "Must contains only alfanumeric symbols and \"-\", \":\", \",\" or <space>")
    private String EAN;
    @NotNull
    @Min(0)
    private double price;
    @NotNull
    @OneToOne
    private Gallery gallery;

    @JsonCreator
    public Product(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("manufacturer") String manufacturer,
                   @JsonProperty("model") String model,
                   @JsonProperty("EAN") String EAN,
                   @JsonProperty("price") double price,
                   @JsonProperty("gallery") long gallery) throws NamingException {
        this.name = name;
        this.description = description;
        this.model = model;
        this.EAN = EAN;
        this.price = price;
        EntityManager entityManager = InitialContext.doLookup("java:/defaultEntityManager");
        this.manufacturer = manufacturer;
        this.gallery = entityManager.find(Gallery.class,gallery);
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
