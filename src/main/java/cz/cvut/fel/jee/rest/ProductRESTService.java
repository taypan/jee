package cz.cvut.fel.jee.rest;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.Product;
import cz.cvut.fel.jee.model.Role;
import cz.cvut.fel.jee.service.LineItemService;
import cz.cvut.fel.jee.service.ProductService;
import cz.cvut.fel.jee.util.PasswordEncryptionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vaclav Rechtberger
 */
@Path("/products")
@ApplicationScoped
public class ProductRESTService {
    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(productService.findAll()).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return Response.status(Response.Status.OK).entity(productService.findById(id)).build();
    }

    @POST
    @Path("/{id:[0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Product product, @PathParam("id") long id) {
        try{
            product.setId(id);
            productService.update(product);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(product).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Product product) {
        try{
            productService.create(product);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(product).build();
    }
}
