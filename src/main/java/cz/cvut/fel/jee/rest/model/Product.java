package cz.cvut.fel.jee.rest.model;

import java.util.UUID;

/**
 * @author Vaclav Rechtberger
 */
public class Product{
    private String uniqueID = UUID.randomUUID().toString();
    private String name;
    private String description;
}
