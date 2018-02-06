package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.ShoppingCart;

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
public class ShoppingCartService extends GenericService<ShoppingCart>{
    @Inject
    EntityManager entityManager;

    public ShoppingCartService() {
        super(ShoppingCart.class, null);
    }

    @PostConstruct
    private void setEntityManager(){
        super.entityManager = this.entityManager;
    }
}
