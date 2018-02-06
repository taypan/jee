package cz.cvut.fel.jee.bean;


import cz.cvut.fel.jee.data.GalleryRepository;
import cz.cvut.fel.jee.data.LineItemRepository;
import cz.cvut.fel.jee.data.ProductRepository;
import cz.cvut.fel.jee.data.ShoppingCartRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ShoppingCartBean {

    @Inject
    private LineItemRepository lineItemRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ShoppingCartRepository shoppingCartRepository;

    public void deleteItemFromShoppingCart(){

    }

    public void addItemToShoppingCart(){

    }

}
