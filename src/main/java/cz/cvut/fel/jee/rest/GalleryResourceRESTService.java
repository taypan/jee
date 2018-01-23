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
@Path("/galleries")
@ApplicationScoped
public class GalleryResourceRESTService {
    private static class Gallery {
        private int id;
        private String name;
        private List<Integer> pictures;

        public Gallery(int id, String name, int picture) {
            this.id = id;
            this.name = name;
            pictures = new ArrayList<>();
            pictures.add(picture);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<Integer> getPictures() {
            return pictures;
        }
    }

    private static Gallery[] data = {
            new Gallery(0, "Hrnek", 0),
            new Gallery(1, "Kartacek", 1),
            new Gallery(2, "Houpaci konik", 2),
            new Gallery(3, "Prasatko", 3),
            new Gallery(4, "Rukavice", 4),
            new Gallery(5, "Zahradni trpaslik", 5)
    };

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllGalleries() {
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
