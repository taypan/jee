package cz.cvut.fel.jee.bean;


import cz.cvut.fel.jee.data.ProductRepository;
import cz.cvut.fel.jee.model.Product;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Named
@ApplicationScoped
public class ProductBean {

    @Inject
    private ProductRepository productRepository;

    @GET
    public Product getItemDetail(long productId){
        return productRepository.findById(productId);
    }


}
