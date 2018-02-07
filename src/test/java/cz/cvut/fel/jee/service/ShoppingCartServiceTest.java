package cz.cvut.fel.jee.service;

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
public class ShoppingCartServiceTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "cz.cvut.fel.jee")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    private ShoppingCartService shoppingCartService;

    @Inject
    private ProductService productService;

    @Inject
    private GalleryService galleryService;

    @Inject
    private LineItemService lineItemService;

    @Test
    public void addItemToNonexistCart() throws NamingException {
        Gallery gallery = new Gallery("name", "base64");
        galleryService.create(gallery);
        Product product = new Product("name", "description", "EAN", 1.1, gallery);
        productService.create(product);
        LineItem lineItem = new LineItem(8, product);
        lineItemService.create(lineItem);

        Account account = new Account(99L, "username", "fullname", new HashSet<>());

        System.out.println("PRODUCT> " + product.getId());

        ShoppingCart shoppingCart = shoppingCartService.addItem(
                account,
                lineItem
        );

        System.out.println("test2");
        System.out.println(account.getId());

        assertEquals(account.getId(), shoppingCart.getAccount());
        assertEquals(1, shoppingCart.getItems().size());
        for(LineItem item : shoppingCart.getItems()) {
            System.out.println("ITEM: " + item);
            assertEquals(8, item.getAmount().intValue());
        }
    }

}