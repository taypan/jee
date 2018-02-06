package cz.cvut.fel.jee.data;

import cz.cvut.fel.jee.model.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.naming.NamingException;

import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ShoppingCartRepositoryTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "cz.cvut.fel.jee")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    private ShoppingCartRepository shoppingCartRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private GalleryRepository galleryRepository;

    @Test
    public void addItemToNonexistCart() throws NamingException {
        Gallery gallery = new Gallery("name", "base64");
        galleryRepository.create(gallery);
        Product product = new Product("name", "description", "model", "EAN", 1.1, gallery.getId());
        productRepository.create(product);
        Account account = new Account(99L, "username", "fullname", new HashSet<>());

        ShoppingCart shoppingCart = shoppingCartRepository.addItem(
                account,
                new LineItem(8, product.getId())
        );
        assertEquals(account, shoppingCart.getAccount());
        assertEquals(1, shoppingCart.getItems().size());
        for(LineItem item : shoppingCart.getItems()) {
            assertEquals(8, item.getAmount().intValue());
        }
    }

}