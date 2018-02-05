package cz.cvut.fel.jee.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class LineItem {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private Product product;

    private int amount;

}
