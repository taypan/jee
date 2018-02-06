package cz.cvut.fel.jee.data;

import cz.cvut.fel.jee.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Vaclav Rechtberger
 */
@ApplicationScoped
public class ProductRepository {
    @Inject
    private EntityManager em;

    public List<Product> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> productRoot = criteria.from(Product.class);
        criteria.select(productRoot);
        return em.createQuery(criteria).getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public void create(Product product) {
        em.persist(product);
    }

}
