package cz.cvut.fel.jee.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Set;

public class ShoppingCart {

    @Id
    @GeneratedValue
    private long id;

    private Set<LineItem> items;

}
