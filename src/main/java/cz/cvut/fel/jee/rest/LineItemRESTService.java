package cz.cvut.fel.jee.rest;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.LineItem;
import cz.cvut.fel.jee.model.Product;
import cz.cvut.fel.jee.model.Role;
import cz.cvut.fel.jee.service.LineItemService;
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
@Path("/items")
@ApplicationScoped
public class LineItemRESTService {
    @Inject
    LineItemService lineItemService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(lineItemService.findAll()).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return Response.status(Response.Status.OK).entity(lineItemService.findById(id)).build();
    }

    @POST
    @Path("/{id:[0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(LineItem lineItem, @PathParam("id") long id) {
        try{
            lineItem.setId(id);
            lineItemService.update(lineItem);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(lineItem).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(LineItem lineItem) {
        try{
            lineItemService.create(lineItem);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(lineItem).build();
    }
}
