package cz.cvut.fel.jee.rest;

import cz.cvut.fel.jee.rest.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vaclav Rechtberger
 */
@Path("/products")
@ApplicationScoped
public class ProductResourceRESTService {

        protected static Map<String,Product> data = new HashMap<>();

        @GET
        @Path("/default")
        @Produces(MediaType.APPLICATION_JSON)
        public Response createDefault() {
                Product product = new Product(
                        "product1",
                        "product1 description...",
                        "manufacturer1",
                        "model1",
                        "EAN1",
                        123.0,
                        "gallery1"
                );
                data.put(product.getUniqueID(),product);
                return Response.status(Response.Status.CREATED).entity(product).build();
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response listAllProducts() {
            return Response.status(Response.Status.OK).entity(data.values()).build();
        }

        @GET
        @Path("/{id:([a-zA-Z0-9]|-)+}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response lookupProductById(@PathParam("id") String id) {
                Product product = data.get(id);
                if (product == null) {
                        throw new WebApplicationException(Response.Status.NOT_FOUND);
                }
                return Response.status(Response.Status.OK).entity(product).build();
        }

        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response createProduct(Product product) {
                data.put(product.getUniqueID(),product);
                return Response.status(Response.Status.CREATED).entity(product).build();
        }

        @POST
        @Path("/{id:([a-zA-Z0-9]|-)+}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response updateProduct(Product productUpdate, @PathParam("id") String id) {
                Product product = data.get(id);

                if (product == null) {
                        throw new WebApplicationException(Response.Status.NOT_FOUND);
                }

                product.setDescription(productUpdate.getDescription());
                product.setEAN(productUpdate.getEAN());
                product.setGallery(productUpdate.getGallery());
                product.setManufacturer(productUpdate.getManufacturer());
                product.setModel(productUpdate.getModel());
                product.setName(productUpdate.getName());
                product.setPrice(productUpdate.getPrice());

                return Response.status(Response.Status.OK).entity(product).build();
        }

        @DELETE
        @Path("/{id:([a-zA-Z0-9]|-)+}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteProduct(Product productUpdate, @PathParam("id") String id) {
                Product product = data.get(id);

                if (product == null) {
                        throw new WebApplicationException(Response.Status.NOT_FOUND);
                }

                data.remove(id);

                return Response.status(Response.Status.OK).build();
        }


}
