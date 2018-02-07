package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Gallery;
import cz.cvut.fel.jee.model.Product;

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
public class GalleryService extends GenericService<Gallery> {
    @Inject
    EntityManager entityManager;

    public GalleryService() {
        super(Gallery.class, null);
    }

    @PostConstruct
    private void setEntityManager(){
        super.entityManager = this.entityManager;
    }
}
