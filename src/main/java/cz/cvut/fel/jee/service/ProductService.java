package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
@ApplicationScoped
public class ProductService extends GenericService<Product> {
    @Inject
    EntityManager entityManager;

    public ProductService() {
        super(Product.class, null);
    }

    @PostConstruct
    private void setEntityManager(){
        super.entityManager = this.entityManager;
    }
}
