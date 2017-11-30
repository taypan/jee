package cz.cvut.fel.jee.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * @author Vaclav Rechtberger
 */
public class Product extends RestObject{
    private String name;
    private String description;
    private String manufacturer;                //ID ref
    private String model;                       //manufacturer model id
    private String EAN;
    private double price;
    private String gallery;                     //ID ref

    @JsonCreator
    public Product(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("manufacturer") String manufacturer,
                   @JsonProperty("model") String model,
                   @JsonProperty("EAN") String EAN,
                   @JsonProperty("price") double price,
                   @JsonProperty("gallery") String gallery) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.model = model;
        this.EAN = EAN;
        this.price = price;
        this.gallery = gallery;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
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
