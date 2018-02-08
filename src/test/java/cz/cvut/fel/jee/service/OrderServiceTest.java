package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Address;
import cz.cvut.fel.jee.model.Gallery;
import cz.cvut.fel.jee.model.Order;
import cz.cvut.fel.jee.model.OrderStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class OrderServiceTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "cz.cvut.fel.jee")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    private OrderService orderService;

    @Inject
    private AddressService addressService;

    @Test
    public void create() {
        Address address = new Address("houseNumber", "street", "city", "code", "county");
        addressService.create(address);
        Order order = new Order(111111L, 0L, 0L, address, 1, new ArrayList<>(), 1.1, OrderStatus.CLOSED);
        orderService.create(order);

        assertTrue(order.getId() > 0);
    }

}