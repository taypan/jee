package cz.cvut.fel.jee.model;

import javax.persistence.Entity;

/**
 * @author Vaclav Rechtberger
 */
@Entity
public enum OrderStatus {
    NEW,PAIED,SHIPPED,CLOSED,ABORTED;
}
