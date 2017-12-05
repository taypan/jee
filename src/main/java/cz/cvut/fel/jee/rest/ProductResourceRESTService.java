package cz.cvut.fel.jee.rest;

import cz.cvut.fel.jee.data.ProductRepository;
import cz.cvut.fel.jee.rest.model.Gallery;
import cz.cvut.fel.jee.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vaclav Rechtberger
 */
@Path("/products")
@ApplicationScoped
public class ProductResourceRESTService {
    private static class Product{
        private long id;
        private String name;
        private String description;
        private String manufacturer;
        private String model;
        private String EAN;
        private double price;
        private long gallery;
        private String category;

        public Product(long id, String name, String description, String manufacturer, String model, String EAN, double price, long gallery, String category) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.manufacturer = manufacturer;
            this.model = model;
            this.EAN = EAN;
            this.price = price;
            this.gallery = gallery;
            this.category = category;
        }

        public String getCategory() {
            return category;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public String getModel() {
            return model;
        }

        public String getEAN() {
            return EAN;
        }

        public double getPrice() {
            return price;
        }

        public long getGallery() {
            return gallery;
        }
    }

    static Product[] data = {
            new Product(0,"Hrnek","Dobrej na caj.","Kerama","abc01","12345678",50.0,0,"nadobi"),
            new Product(1,"Kartacek","Dobrej na zuby.","Curaprox","abc02","12345679",100.0,1,"hygiena"),
            new Product(2,"Houpaci konik","Dobrej na houpani.","Drevotvor","abc03","12345610",499.9,2,"hracky"),
            new Product(3,"Prasatko","Dobry na love.","Kerama","abc04","12345611",50.0,3,"doplnky"),
            new Product(4,"Rukavice pracovni","Dobre na ruce.","Texon","abc05","12345612",69.9,4,"prace"),
            new Product(5,"Zahradni trpaslik","Dobrej do travy.","Kerama","abc06","12345613",555.5,5,"zahrada")
    };

    @Inject
    ProductService productService;

    @Inject
    private ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllProducts() {
        return Response.status(Response.Status.OK).entity(data).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lookupProductById(@PathParam("id") int id) {
        try{return Response.status(Response.Status.OK).entity(data[id]).build();}
        catch (Exception e){return Response.status(Response.Status.NOT_FOUND).build(); }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(String json) {
        return Response.status(Response.Status.CREATED).entity("{\"id\":1}").build();
    }

    @POST
    @Path("/{id:([a-zA-Z0-9]|-)+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(String json, @PathParam("id") long id) {

        return Response.status(Response.Status.OK).entity(json).build();
    }
















    /*
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
                    try{
                            productService.createProduct(product);
                    }catch (Exception e){
                            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                    }

                    return Response.status(Response.Status.CREATED).entity(product).build();
            }
    */
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllProducts() {
        return Response.status(Response.Status.OK).entity(productRepository.findAll()).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lookupProductById(@PathParam("id") long id) {
        Product product = productRepository.findById(id);
        return Response.status(Response.Status.OK).entity(product).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        try {
            productService.createProduct(product);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.CREATED).entity(product).build();
    }

    @POST
    @Path("/{id:([a-zA-Z0-9]|-)+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product productUpdate, @PathParam("id") long id) {
        try {
            productUpdate.setId(id);
            productService.updateProduct(productUpdate);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(productUpdate).build();
    }

/*
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
        }*/


}
