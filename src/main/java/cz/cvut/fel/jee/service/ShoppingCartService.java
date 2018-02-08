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
        LineItem newItem = null;
        for(LineItem lineItem : shoppingCart.getItems()){
            if(lineItem.getProduct().getId() == item.getProduct().getId()){
                newItem = lineItem;
                break;
            }
        }

        System.out.println("FOUNDED ITEM: " + newItem);
        if(newItem == null){
            newItem = item;
            shoppingCart.getItems().add(newItem);
            create(shoppingCart);
        }else{
            shoppingCart.getItems().remove(newItem);
            newItem.setAmount(newItem.getAmount() + item.getAmount());
            shoppingCart.getItems().add(newItem);
            update(shoppingCart);
        }

        System.out.println("ADD ITEM: " + item.getId());
        System.out.println("GET FIRST ITEM ID: " + shoppingCart.getItems().get(0).getId());
        System.out.println("GET FIRST ITEM COUNT: " + shoppingCart.getItems().get(0).getAmount());

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
