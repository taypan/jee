package cz.cvut.fel.jee.data;

import cz.cvut.fel.jee.model.LineItem;
import cz.cvut.fel.jee.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class LineItemRepository {
    @Inject
    private EntityManager em;

    public List<LineItem> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LineItem> criteria = cb.createQuery(LineItem.class);
        Root<LineItem> productRoot = criteria.from(LineItem.class);
        criteria.select(productRoot);
        return em.createQuery(criteria).getResultList();
    }

    public LineItem findById(Long id) {
        return em.find(LineItem.class, id);
    }
}
