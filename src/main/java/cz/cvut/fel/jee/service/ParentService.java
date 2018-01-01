package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.rest.model.old.Parent;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Vaclav Rechtberger
 */
@ApplicationScoped
@Stateless
public class ParentService extends GenericService<Parent> {
    @Inject
    EntityManager entityManager;

    public ParentService() {
        super(Parent.class,null);
    }

    @PostConstruct
    void setEntityManager(){
        super.entityManager = this.entityManager;
    }
}
