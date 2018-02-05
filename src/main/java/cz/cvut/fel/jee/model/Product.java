package cz.cvut.fel.jee.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.inject.Named;
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
@Data
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
    private String EAN;

    @NotNull
    @Min(0)
    private double price;

    @NotNull
    @OneToOne
    @Named("gallery_id")
    private Gallery gallery;

    //TODO do we need manufacturer?
    @JsonCreator
    public Product(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("model") String model,
                   @JsonProperty("EAN") String EAN,
                   @JsonProperty("price") double price,
                   @JsonProperty("gallery") long gallery) throws NamingException {
        this.name = name;
        this.description = description;
        this.EAN = EAN;
        this.price = price;
        EntityManager entityManager = InitialContext.doLookup("java:/defaultEntityManager");
        this.gallery = entityManager.find(Gallery.class,gallery);
    }

    public Product() {
    }

}
