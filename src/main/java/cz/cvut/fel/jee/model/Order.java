package cz.cvut.fel.jee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Vaclav Rechtberger
 */
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

    @OneToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("address_id")
    @JsonIdentityReference(alwaysAsId=true)
    private Address address;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("account_id")
    @JsonIdentityReference(alwaysAsId=true)
    private Account account;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("item_ids")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<LineItem> items;

    @NotNull
    private double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(long ordered, long shipped, long paid, Address address, Account account, Set<LineItem> items, double total, OrderStatus status) {
        this.ordered = ordered;
        this.shipped = shipped;
        this.paid = paid;
        this.address = address;
        this.account = account;
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
        this.account = entityManager.find(Account.class,accountId);
        this.items = itemsIds.stream().map(itemId->entityManager.find(LineItem.class,itemId)).collect(Collectors.toSet());
        this.total = total;
        this.status = status;

    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrdered() {
        return ordered;
    }

    public void setOrdered(long ordered) {
        this.ordered = ordered;
    }

    public long getShipped() {
        return shipped;
    }

    public void setShipped(long shipped) {
        this.shipped = shipped;
    }

    public long getPaid() {
        return paid;
    }

    public void setPaid(long paid) {
        this.paid = paid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<LineItem> getItems() {
        return items;
    }

    public void setItems(Set<LineItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
