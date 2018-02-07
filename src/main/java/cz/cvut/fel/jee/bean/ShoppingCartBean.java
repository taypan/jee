package cz.cvut.fel.jee.bean;


import cz.cvut.fel.jee.data.LineItemRepository;
import cz.cvut.fel.jee.model.LineItem;
import cz.cvut.fel.jee.model.Product;
import cz.cvut.fel.jee.service.ShoppingCartService;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

//TODO pak predelat na vaskovi classy
@Named
@ApplicationScoped
public class ShoppingCartBean {

    @Inject
    private LineItemRepository lineItemRepository;

    @Inject
    private LoginBean loginBean;

    @Inject
    private ShoppingCartService shoppingCartService;

    public void deleteItemFromShoppingCart(){

    }

    public void addItemToShoppingCart(Product product, int amount) throws NamingException {
        shoppingCartService.addItem(
                loginBean.loggedAccount(),
                new LineItem(amount, product)
        );
    }

}
