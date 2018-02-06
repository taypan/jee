package cz.cvut.fel.jee.rest;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.Order;
import cz.cvut.fel.jee.model.Role;
import cz.cvut.fel.jee.service.LineItemService;
import cz.cvut.fel.jee.service.OrderQueueService;
import cz.cvut.fel.jee.service.OrderService;
import cz.cvut.fel.jee.util.PasswordEncryptionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.ReflectionException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vaclav Rechtberger
 */
@Path("/orders")
@ApplicationScoped
public class OrderRESTService {
    @Inject
    OrderService orderService;

    @Inject
    OrderQueueService orderQueueService;

    //TODO 200 OK + null nebo 204 No Content
    @GET
    @Path("/queue")
    @Produces(MediaType.APPLICATION_JSON)
    public Response nextOrder() throws JMSException {
        String messageId = orderQueueService.receiveMessage();
        if(messageId == null)
            return Response.noContent().build();
        return Response.status(Response.Status.OK).entity(
                orderService.findById(
                        Long.valueOf(
                                messageId
                        )))
                .build();
    }

    //TODO
    @HEAD
    @Path("/queue/hasNext")
    public Response hasNext() throws JMSException {
        return Response.ok().header("hasNext",new Boolean(orderQueueService.hasNextMessage())).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(orderService.findAll()).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return Response.status(Response.Status.OK).entity(orderService.findById(id)).build();
    }

    @POST
    @Path("/{id:[0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Order order, @PathParam("id") long id) {
        try{
            order.setId(id);
            orderService.update(order);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(order).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Order order) {
        try{
            orderService.create(order);
            orderQueueService.sendMessage(""+order.getId());
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(order).build();
    }
}
