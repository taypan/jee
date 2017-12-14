package cz.cvut.fel.jee.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Vaclav Rechtberger
 */
@Entity
@Table(name="lineitems")
public class LineItem {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private Integer amount;

    @ManyToOne
    private Product product;
}
