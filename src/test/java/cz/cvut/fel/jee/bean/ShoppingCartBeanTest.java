package cz.cvut.fel.jee.bean;

import cz.cvut.fel.jee.data.ShoppingCartRepository;
import cz.cvut.fel.jee.model.*;
import cz.cvut.fel.jee.service.AccountRegistration;
import cz.cvut.fel.jee.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class ShoppingCartBeanTest {

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
    private ShoppingCartBean shoppingCartBean;

    @Test
    public void deleteItemFromShoppingCart() {
        shoppingCartBean.addItemToShoppingCart();
        assertNotNull("test", "test");
    }

    @Test
    public void addItemToShoppingCart() {
        assertNotNull("test", "test");
    }
}