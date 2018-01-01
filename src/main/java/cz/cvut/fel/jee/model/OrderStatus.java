package cz.cvut.fel.jee.model;

import javax.persistence.Entity;

/**
 * @author Vaclav Rechtberger
 */
public enum OrderStatus {
    NEW,PAID,SHIPPED,CLOSED,ABORTED;
}
