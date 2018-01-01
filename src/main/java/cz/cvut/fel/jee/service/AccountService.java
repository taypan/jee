package cz.cvut.fel.jee.service;


import cz.cvut.fel.jee.model.Account;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Vaclav Rechtberger
 */
@Stateless
@ApplicationScoped
public class AccountService extends GenericService<Account>{
    @Inject
    EntityManager entityManager;

    public AccountService() {
        super(Account.class,null);
    }

    @PostConstruct
    private void setEntityManager(){
        super.entityManager = this.entityManager;
    }
}
