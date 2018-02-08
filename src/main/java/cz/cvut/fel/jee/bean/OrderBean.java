package cz.cvut.fel.jee.bean;


import com.sun.org.apache.xpath.internal.operations.Or;
import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.LineItem;
import cz.cvut.fel.jee.model.Order;
import cz.cvut.fel.jee.model.ShoppingCart;
import cz.cvut.fel.jee.service.LineItemService;
import cz.cvut.fel.jee.service.OrderService;
import cz.cvut.fel.jee.service.ProductService;
import cz.cvut.fel.jee.service.ShoppingCartService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
@ManagedBean(name = "orderBean")
public class OrderBean {

    @Inject
    private OrderService orderService;

    @Inject
    private LoginBean loginBean;

    public List<Order> getAllItem(){
        return findByAccount(loginBean.loggedAccount());
    }

    public List<Order> findByAccount(Account account){
        System.out.println("TEST QUERY2: " + account.getId());

        return orderService.findAll();
    }
}
