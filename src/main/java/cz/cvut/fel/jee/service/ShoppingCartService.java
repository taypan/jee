package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.LineItem;
import cz.cvut.fel.jee.model.ShoppingCart;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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

    public void removeLineItem(Account account, LineItem item){
        ShoppingCart shoppingCart = findByAccount(account);
        LineItem foundedItem = findItemInCart(shoppingCart, item);
        shoppingCart.getItems().remove(foundedItem);
        update(shoppingCart);
    }

    public ShoppingCart addItem(Account account, LineItem item) {
        ShoppingCart shoppingCart = findByAccount(account);
        if(shoppingCart == null){
            System.out.println("Test shopping cart ok");
            shoppingCart = new ShoppingCart(account.getId());
        }

        System.out.println();
        LineItem newItem = findItemInCart(shoppingCart, item);

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
        //todo by account
        List<ShoppingCart> all = findAll();
        if (all.size() == 0){
            return null;
        }else{
            return all.get(0);
        }
    }

    private LineItem findItemInCart(ShoppingCart shoppingCart, LineItem item){
        LineItem foundedItem = null;
        for(LineItem lineItem : shoppingCart.getItems()){
            if(lineItem.getProduct().getId() == item.getProduct().getId()){
                foundedItem = lineItem;
                break;
            }
        }
        return foundedItem;
    }

}
