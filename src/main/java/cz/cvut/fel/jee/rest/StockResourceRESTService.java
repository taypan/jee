package cz.cvut.fel.jee.rest;

import cz.cvut.fel.jee.rest.model.Product;
import cz.cvut.fel.jee.rest.model.Stock;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vaclav Rechtberger
 */
@Path("/stocks")
@ApplicationScoped
public class StockResourceRESTService {
    protected static Map<String,Stock> data = new HashMap<>();


}
