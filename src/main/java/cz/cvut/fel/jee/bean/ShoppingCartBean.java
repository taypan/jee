package cz.cvut.fel.jee.bean;


import cz.cvut.fel.jee.data.LineItemRepository;
import cz.cvut.fel.jee.model.LineItem;
import cz.cvut.fel.jee.model.Product;
import cz.cvut.fel.jee.model.ShoppingCart;
import cz.cvut.fel.jee.service.LineItemService;
import cz.cvut.fel.jee.service.ProductService;
import cz.cvut.fel.jee.service.ShoppingCartService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
@ManagedBean(name = "shoppingBean")
public class ShoppingCartBean {

    @Inject
    private ProductService productService;

    @Inject
    private LoginBean loginBean;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Inject
    private LineItemService lineItemService;

    @Inject
    private FacesContext context;

    public List<LineItem> getAllItem(){
        ShoppingCart byAccount = shoppingCartService.findByAccount(loginBean.loggedAccount());
        if(byAccount==null){
            return new ArrayList<>();
        }
        return byAccount.getItems();
    }

    public void deleteItemFromShoppingCart(long listItemId){
        shoppingCartService.removeLineItem(
                loginBean.loggedAccount(),
                lineItemService.findById(listItemId)
        );
    }

    public void addItemToShoppingCart() throws IOException {
        Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        addItemToShoppingCart(
                Long.parseLong(params.get("productId")),
                Integer.parseInt(params.get("amount"))
        );
    }

    public void addItemToShoppingCart(long productId, int amount) throws IOException {
        System.out.println("TEST product: " + productId);
        System.out.println("TEST: amount" + amount);
        LineItem lineItem = new LineItem(amount, productService.findById(productId));
        lineItemService.create(lineItem);
        shoppingCartService.addItem(
                loginBean.loggedAccount(),
                lineItem
        );
        context.getExternalContext().redirect("detail.jsf?product_id=" + productId);
    }

}
