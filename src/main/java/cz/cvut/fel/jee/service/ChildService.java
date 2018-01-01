package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.rest.model.old.Child;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Vaclav Rechtberger
 */
@ApplicationScoped
@Stateless
public class ChildService extends GenericService<Child> {
    @Inject
    EntityManager entityManager;
    public ChildService() {
        super(Child.class, null);
    }

    @PostConstruct
    void setEntityManager(){
        super.entityManager = this.entityManager;
    }
}
