package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Gallery;
import cz.cvut.fel.jee.model.LineItem;
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

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class LineItemServiceTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "cz.cvut.fel.jee")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    private LineItemService lineItemService;

    @Inject
    private ProductService productService;

    @Test
    public void create() throws NamingException {
        Product product = new Product("name", "description", "ean", 1.1, null);
        productService.create(product);
        LineItem lineItem = new LineItem(8, product);
        lineItemService.create(lineItem);

        assertTrue(lineItem.getId() > 0);
    }

}