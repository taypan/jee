package cz.cvut.fel.jee.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Vaclav Rechtberger
 */
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private long ordered;

    private long shipped;

    private long paid;

    @OneToOne
    private Address address;

    @ManyToOne
    private Account account;

    @OneToMany
    private Set<LineItem> items;

    @NotNull
    private double total;

    private OrderStatus status;
}
