package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Order;
import cz.cvut.fel.jee.validation.anotations.ValidOrder;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Vaclav Rechtberger
 */
@Stateless
@ApplicationScoped
public class OrderService extends GenericService<Order> {
    @Inject
    EntityManager entityManager;

    public OrderService() {
        super(Order.class, null);
    }

    @PostConstruct
    private void setEntityManager(){
        super.entityManager = this.entityManager;
    }

}
