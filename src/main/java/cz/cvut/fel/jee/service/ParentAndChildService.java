package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Child;
import cz.cvut.fel.jee.model.Parent;

import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Vaclav Rechtberger
 */
@Stateless
@ApplicationScoped
public class ParentAndChildService {
    @Inject
    EntityManager em;

    public void createParent(Parent parent) throws Exception {
        System.out.println("Creating parent " + parent.getName());
        em.persist(parent);
        //productEventSrc.fire(product);
    }

    public void createChild(Child child) throws Exception {
        System.out.println("Creating child " + child.getName());
        em.persist(child);
        //productEventSrc.fire(product);
    }

    public List<Parent> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Parent> criteria = cb.createQuery(Parent.class);
        Root<Parent> parentRoot = criteria.from(Parent.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(parentRoot);
        return em.createQuery(criteria).getResultList();
    }

    public Child findChildById(Long id){
        return em.find(Child.class, id);
    }

    public void updateParent(Parent parent) throws Exception {
        Parent parent2 = em.find(Parent.class,parent.getId());

        parent2.setName(parent.getName());
        parent2.setChild(parent.getChild());
    }
}
