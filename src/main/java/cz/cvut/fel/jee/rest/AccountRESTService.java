package cz.cvut.fel.jee.rest;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.Role;
import cz.cvut.fel.jee.service.AccountService;
import cz.cvut.fel.jee.util.PasswordEncryptionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Vaclav Rechtberger
 */
@Path("/accounts")
@ApplicationScoped
public class AccountRESTService {
    @Inject
    AccountService accountService;

    @GET
    @Path("/default")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDefault(){
        Account account = new Account();
        account.setUsername("user1");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        roles.add(Role.EMPLOYEE);
        account.setRoles(roles);
//        account.setSalt(PasswordEncryptionService.generateSalt());
//        try {
//            account.setPasswordHash(PasswordEncryptionService.computeSaltedHash("default",account.getSalt(),PasswordEncryptionService.ALGORITHM_MD5));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return Response.serverError().build();
//        }

        accountService.create(account);

        return Response.status(Response.Status.CREATED).entity(account).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(accountService.findAll()).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return Response.status(Response.Status.OK).entity(accountService.findById(id)).build();
    }

    @POST
    @Path("/{id:[0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Account account, @PathParam("id") long id) {
        try{
            account.setId(id);
            accountService.update(account);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(account).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Account account) {
        try{
            accountService.create(account);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(account).build();
    }
}


