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

//TODO pak predelat na vaskovi classy
@Named
@ApplicationScoped
public class ShoppingCartBean {

    @Inject
    private LineItemRepository lineItemRepository;

    @Inject
    private LoginBean loginBean;

    @Inject
    private ShoppingCartRepository shoppingCartRepository;

    public void deleteItemFromShoppingCart(){

    }

    public void addItemToShoppingCart(long productId, int amount) throws NamingException {
        shoppingCartRepository.addItem(
                loginBean.loggedAccount(),
                new LineItem(amount, productId)
        );
    }

}
