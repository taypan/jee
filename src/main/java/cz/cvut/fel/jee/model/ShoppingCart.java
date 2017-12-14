package cz.cvut.fel.jee.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Vaclav Rechtberger
 */
@Entity
@Table(name="shoppingcarts")
public class ShoppingCart {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Account account;

    @OneToMany
    private Set<LineItem> items;
}
