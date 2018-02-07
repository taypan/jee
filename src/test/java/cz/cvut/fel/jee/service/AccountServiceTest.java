package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.Gallery;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class AccountServiceTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "cz.cvut.fel.jee")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    private AccountService accountService;

    @Test
    public void create() {
        Account account = new Account("username", "fullname", new HashSet<>());
        accountService.create(account);

        assertTrue(account.getId() > 0);
    }

}