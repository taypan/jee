package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.LineItem;
import cz.cvut.fel.jee.model.ShoppingCart;
import cz.cvut.fel.jee.model.ShoppingCart_;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Vaclav Rechtberger
 */
@Stateless
@ApplicationScoped
public class ShoppingCartService extends GenericService<ShoppingCart>{

    @Inject
    EntityManager entityManager;

    public ShoppingCartService() {
        super(ShoppingCart.class, null);
    }

    @PostConstruct
    private void setEntityManager(){
        super.entityManager = this.entityManager;
    }

    public ShoppingCart addItem(Account account, LineItem item) {
        ShoppingCart shoppingCart = findByAccount(account);
        if(shoppingCart == null){
            System.out.println("Test shopping cart ok");
            shoppingCart = new ShoppingCart(account.getId());
        }

        System.out.println();
        System.out.println("Line item OK");
        LineItem newItem = null;
        for(LineItem lineItem : shoppingCart.getItems()){
            if(lineItem.getId() == item.getId()){
                newItem = new LineItem(lineItem.getAmount() + item.getAmount(), lineItem.getProduct());
                shoppingCart.getItems().remove(lineItem);
            }
        }

        System.out.println("FOUNDED ITEM: " + newItem);
        if(newItem == null){
            newItem = item;
            System.out.println("NEW ITEM: " + newItem);
        }

        System.out.println("ADD ITEM: " + item.getId());
        shoppingCart.getItems().add(newItem);
        System.out.println("GET ITEMS: " + shoppingCart.getItems().get(0).getId());

        create(shoppingCart);

        return shoppingCart;
    }

    public ShoppingCart findByAccount(Account account){
        System.out.println("TEST QUERY2: " + account.getId());
        List<ShoppingCart> all = findAll();
        if (all.size() == 0){
            return null;
        }else{
            return all.get(0);
        }
    }


}
