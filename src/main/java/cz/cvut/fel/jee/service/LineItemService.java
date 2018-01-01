package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.LineItem;

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
public class LineItemService extends GenericService<LineItem> {
    @Inject
    EntityManager entityManager;

    public LineItemService() {
        super(LineItem.class, null);
    }

    @PostConstruct
    private void setEntityManager(){
        super.entityManager = this.entityManager;
    }
}
