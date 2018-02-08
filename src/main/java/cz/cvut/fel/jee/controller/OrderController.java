/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.cvut.fel.jee.controller;

import cz.cvut.fel.jee.bean.LoginBean;
import cz.cvut.fel.jee.bean.ShoppingCartBean;
import cz.cvut.fel.jee.model.*;
import cz.cvut.fel.jee.service.AddressService;
import cz.cvut.fel.jee.service.OrderService;
import cz.cvut.fel.jee.service.ShoppingCartService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.Map;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://www.cdi-spec.org/faq/#accordion6
@Model
public class OrderController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private OrderService orderService;

    @Inject
    private AddressService addressService;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Inject
    private LoginBean loginBean;

    @Inject
    private ShoppingCartBean shoppingCart;

    @Produces
    @Named
    private Address newAddress = new Address();

    public void register() {
        try {
            Map<String,String> map = facesContext.getExternalContext().getRequestParameterMap();
            newAddress = new Address(
                    map.get("reg:houseNumber"),
                    map.get("reg:street"),
                    map.get("reg:city"),
                    map.get("reg:code"),
                    map.get("reg:country")
            );

            long userId = loginBean.loggedAccount().getId();
            addressService.create(newAddress);
            System.out.println("zkouska: " + newAddress.getCity());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Order order = new Order(
                    timestamp.getTime(),
                    0,
                    0,
                    newAddress,
                    userId,
                    shoppingCart.getAllItem(),
                    shoppingCart.getItemPrice(),
                    OrderStatus.NEW
            );
            orderService.create(order);
            if(order.getId() > 0){
                shoppingCartService.deleteByAccount(loginBean.loggedAccount());
            }
        } catch (Exception e) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, getRootErrorMessage(e), "Unsucessfull order");
            facesContext.addMessage(null, m);
        }
    }

    private String getRootErrorMessage(Exception e) {
        System.out.println("Error");
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            return errorMessage;
        }
        Throwable t = e;
        while (t != null) {
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        return errorMessage;
    }
}
