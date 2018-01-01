package cz.cvut.fel.jee.rest;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cz.cvut.fel.jee.rest.model.old.Child;
import cz.cvut.fel.jee.rest.model.old.Parent;
import cz.cvut.fel.jee.service.ParentAndChildService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Vaclav Rechtberger
 */
@Path("/parents")
@ApplicationScoped
public class ParentResourceRESTService {

    @Inject
    EntityManager entityManager;
    @Inject
    ParentAndChildService service;


    @GET
    @Path("/default")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDefault() {
        Child child = new Child("child");
        Parent parent = new Parent("parent",child);
        try{
            service.createChild(child);
            System.out.println("child created");
            service.createParent(parent);
            System.out.println("parent created");
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.CREATED).entity(parent).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllProducts() {
        System.out.println("getAll");
        return Response.status(Response.Status.OK).entity(service.findAll()).build();
    }


    @POST
    @Path("/{id:([a-zA-Z0-9]|-)+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Parent parent, @PathParam("id") long id) {
        try{
            parent.setId(id);
            service.updateParent(parent);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(parent).build();
    }
}
