package cz.cvut.fel.jee.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.inject.Named;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name="products")
public class Product implements Serializable, Identifiable {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Must contains only alfanumeric symbols and \"-\", \":\", \",\" or <space>")
    private String name;

    @NotNull
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Must contains only alfanumeric symbols and \"-\", \":\", \",\" or <space>")
    private String description;

    @NotNull
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Must contains only alfanumeric symbols and \"-\", \":\", \",\" or <space>")
    private String EAN;

    @NotNull
    @Min(0)
    private double price;

    @OneToOne
    @Named("gallery_id")
    private Gallery gallery;

    @JsonCreator
    public Product(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("EAN") String EAN,
                   @JsonProperty("price") double price,
                   @JsonProperty("gallery") Gallery gallery) throws NamingException {
        System.out.println("name " + name);
        System.out.println("description " + description);
        System.out.println("EAN " + EAN);
        System.out.println("price " + price);
        this.name = name;
        this.description = description;
        this.EAN = EAN;
        this.price = price;
        this.gallery = gallery;
    }

    public Product() {
    }

}
