package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Gallery;
import cz.cvut.fel.jee.model.Product;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.naming.NamingException;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ProductServiceTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "cz.cvut.fel.jee")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    private ProductService productService;

    @Inject
    private GalleryService galleryService;

    @Test
    public void create() throws NamingException, InterruptedException {
        Gallery gallery = new Gallery("name", "base64");
        galleryService.create(gallery);
        Product product = new Product("name", "description", "EAN", 1.1, gallery);
        productService.create(product);

        assertTrue(product.getId() > 0);
    }

}