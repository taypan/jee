package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.Address;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Vaclav Rechtberger
 */
@Stateless
@ApplicationScoped
public class AddressService extends GenericService<Address> {
    @Inject
    EntityManager entityManager;

    @Inject
    private Event<Address> addressEvent;

    public AddressService() {
        super(Address.class, null);
    }

    @PostConstruct
    private void setEntityManager(){
        super.entityManager = this.entityManager;
    }

    public void create(Address address) {
        entityManager.persist(address);
        addressEvent.fire(address);
    }

}
