package cz.cvut.fel.jee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vaclav Rechtberger
 */
@Data
@Entity
@Table(name="orders")
public class Order implements Serializable, Identifiable {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private long ordered;

    private long shipped;

    private long paid;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("address_id")
    @JsonIdentityReference(alwaysAsId=true)
    private Address address;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("account_id")
    @JsonIdentityReference(alwaysAsId=true)
    private long accountId;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("item_ids")
    @JsonIdentityReference(alwaysAsId=true)
    private List<LineItem> items;

    @NotNull
    private double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(long ordered, long shipped, long paid, Address address, long accountId, List<LineItem> items, double total, OrderStatus status) {
        this.ordered = ordered;
        this.shipped = shipped;
        this.paid = paid;
        this.address = address;
        this.accountId = accountId;
        this.items = items;
        this.total = total;
        this.status = status;
    }

    public Order(@JsonProperty("ordered") long ordered,
                 @JsonProperty("shipped") long shipped,
                 @JsonProperty("paid") long paid,
                 @JsonProperty("address_id") long addressId,
                 @JsonProperty("account_id")long accountId,
                 @JsonProperty("item_ids")Set<Long> itemsIds,
                 @JsonProperty("total") double total,
                 @JsonProperty("status")OrderStatus status) throws NamingException {
        this.ordered = ordered;
        this.shipped = shipped;
        this.paid = paid;
        final EntityManager entityManager = InitialContext.doLookup("java:/defaultEntityManager");
        this.address = entityManager.find(Address.class,addressId);
        this.accountId = accountId;
        this.items = itemsIds.stream().map(itemId->entityManager.find(LineItem.class,itemId)).collect(Collectors.toList());
        this.total = total;
        this.status = status;

    }

    public Order() {
    }

}
