package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.rest.model.old.Child;
import cz.cvut.fel.jee.rest.model.old.Parent;

import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Vaclav Rechtberger
 */
@Stateless
@ApplicationScoped
public class ParentAndChildService {
    @Inject
    GenericService<Child> childService;// = new GenericService<>();
    @Inject
    GenericService<Parent> parentService;// = new GenericService<>();

    public void createParent(Parent parent) throws Exception {
        System.out.println("Creating parent " + parent.getName());
        parentService.create(parent);
        //em.persist(parent);
        //productEventSrc.fire(product);
    }

    public void createChild(Child child) throws Exception {
        System.out.println("Creating child " + child.getName());
        childService.create(child);
        //productEventSrc.fire(product);
    }

    public List<Parent> findAll() {
        /*CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Parent> criteria = cb.createQuery(Parent.class);
        Root<Parent> parentRoot = criteria.from(Parent.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(parentRoot);
        return em.createQuery(criteria).getResultList();*/
        return parentService.findAll();
    }

    public Child findChildById(Long id){
        return childService.findById(id);
    }

    public void updateParent(Parent parent) throws Exception {
        parentService.update(parent);
    }
}
