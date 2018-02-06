package cz.cvut.fel.jee.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity(name = "LineItem")
@Table(name="lineitems")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItem implements Serializable,Identifiable{
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private Integer amount;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("product_id")
    @JsonIdentityReference(alwaysAsId=true)
    private Product product;

    public LineItem(Integer amount, Product product) {
        this.amount = amount;
        this.product = product;
    }

    @JsonCreator
    public LineItem(@JsonProperty("amount") Integer amount, @JsonProperty("product_id") Long productId) throws NamingException {
        this(amount,((EntityManager)InitialContext.doLookup("java:/defaultEntityManager")).find(Product.class,productId));
    }

}