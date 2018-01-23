package cz.cvut.fel.jee.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vaclav Rechtberger
 */
@Path("/pictures")
@ApplicationScoped
public class PictureResourceRESTService {
    private static class Picture {
        private int id;
        private String name;
        private String file;

        public Picture(int id, String name, String file) {
            this.id = id;
            this.name = name;
            this.file = file;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getFile() {
            return file;
        }
    }

    private static Picture[] data = {
            new Picture(0, "Hrnek", "hrnek.jpg"),
            new Picture(1, "Kartacek", "kartacek.jpg"),
            new Picture(2, "Houpaci konik", "konik.jpg"),
            new Picture(3, "Prasatko", "prasatko.jpg"),
            new Picture(4, "Rukavice", "rukavice.jpeg"),
            new Picture(5, "Zahradni trpaslik", "trpaslik.jpg")
    };

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllPictures() {
        return Response.status(Response.Status.OK).entity(data).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lookupGalleryById(@PathParam("id") int id) {
        try {
            return Response.status(Response.Status.OK).entity(data[id]).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGallery(String json) {
        return Response.status(Response.Status.CREATED).entity("{\"id\":1}").build();
    }

    @POST
    @Path("/{id:([a-zA-Z0-9]|-)+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGallery(String json, @PathParam("id") long id) {
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
