package cz.cvut.fel.jee.data;

import cz.cvut.fel.jee.model.LineItem;
import cz.cvut.fel.jee.model.ShoppingCart;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class ShoppingCartRepository {
    @Inject
    private EntityManager em;

    public List<ShoppingCart> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ShoppingCart> criteria = cb.createQuery(ShoppingCart.class);
        Root<ShoppingCart> productRoot = criteria.from(ShoppingCart.class);
        criteria.select(productRoot);
        return em.createQuery(criteria).getResultList();
    }

    public ShoppingCart findById(Long id) {
        return em.find(ShoppingCart.class, id);
    }
}
