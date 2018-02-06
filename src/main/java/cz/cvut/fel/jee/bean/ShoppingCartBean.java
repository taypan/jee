package cz.cvut.fel.jee.bean;


import cz.cvut.fel.jee.data.GalleryRepository;
import cz.cvut.fel.jee.data.LineItemRepository;
import cz.cvut.fel.jee.data.ProductRepository;
import cz.cvut.fel.jee.data.ShoppingCartRepository;
import cz.cvut.fel.jee.model.LineItem;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

@Named
@ApplicationScoped
public class ShoppingCartBean {

    @Inject
    private LineItemRepository lineItemRepository;

    @Inject
    private ShoppingCartRepository shoppingCartRepository;

    public void deleteItemFromShoppingCart(){

    }

    public void addItemToShoppingCart(long itemId, int amount) throws NamingException {
        LineItem lineItem = new LineItem(amount, itemId);
    }

}
