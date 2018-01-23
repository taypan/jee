package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.data.ProductRepository;
import cz.cvut.fel.jee.model.User;
import cz.cvut.fel.jee.rest.model.Product;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.logging.Logger;

/**
 * @author Vaclav Rechtberger
 */
@Stateless
public class ProductService {
    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private Event<Product> productEventSrc;

    public void createProduct(Product product) throws Exception {
        log.info("Creating product " + product.getName());
        em.persist(product);
        productEventSrc.fire(product);
    }

    public void updateProduct(Product product) throws Exception {
        log.info("Updating product " + product.getName());

        Product product2 = em.find(Product.class,product.getId());

        product2.setPrice(product.getPrice());

        productEventSrc.fire(product2);
    }
}
