package cz.cvut.fel.jee.rest;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.Role;
import cz.cvut.fel.jee.model.ShoppingCart;
import cz.cvut.fel.jee.service.LineItemService;
import cz.cvut.fel.jee.service.ShoppingCartService;
import cz.cvut.fel.jee.util.PasswordEncryptionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Vaclav Rechtberger
 */
@Path("/shoppingcarts")
@ApplicationScoped
public class ShoppingCartRESTService {
    @Inject
    ShoppingCartService shoppingCartService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(shoppingCartService.findAll()).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return Response.status(Response.Status.OK).entity(shoppingCartService.findById(id)).build();
    }

    @POST
    @Path("/{id:[0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ShoppingCart shoppingCart, @PathParam("id") long id) {
        try{
            shoppingCart.setId(id);
            shoppingCartService.update(shoppingCart);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(shoppingCart).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ShoppingCart shoppingCart) {
        try{
            shoppingCartService.create(shoppingCart);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(shoppingCart).build();
    }
}
