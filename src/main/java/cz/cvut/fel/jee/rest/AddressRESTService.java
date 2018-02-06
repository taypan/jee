package cz.cvut.fel.jee.rest;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.Address;
import cz.cvut.fel.jee.model.Role;
import cz.cvut.fel.jee.service.AddressService;
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
@Path("/addresses")
@ApplicationScoped
public class AddressRESTService {
    @Inject
    AddressService addressService;


    @GET
    @Path("/default")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDefault(){

        Address address = new Address("123","Dvorakova","Praha","10000","Ceska republika");

        addressService.create(address);

        return Response.status(Response.Status.CREATED).entity(address).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(addressService.findAll()).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return Response.status(Response.Status.OK).entity(addressService.findById(id)).build();
    }

    @POST
    @Path("/{id:[0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Address address, @PathParam("id") long id) {
        try{
            address.setId(id);
            addressService.update(address);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(address).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Address address) {
        try{
            addressService.create(address);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(address).build();
    }

}
