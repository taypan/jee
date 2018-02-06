package cz.cvut.fel.jee.model;

import com.fasterxml.jackson.annotation.*;

import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vaclav Rechtberger
 */
@Entity
@Table(name="shoppingcarts")
public class ShoppingCart implements Serializable, Identifiable {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JsonProperty("account_id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Account account;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("item_ids")
    @JsonIdentityReference(alwaysAsId=true)
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "shoppingcart", cascade = CascadeType.ALL)
    //@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    private Set<LineItem> items;

    public ShoppingCart(Account account, Set<LineItem> items) {
        this.account = account;
        this.items = items;
    }

    @JsonCreator
    public ShoppingCart(@JsonProperty("account_id") long accountId, @JsonProperty("item_ids") Set<Long> itemsIds) throws NamingException {
        final EntityManager entityManager = InitialContext.doLookup("java:/defaultEntityManager");
        this.account = entityManager.find(Account.class, accountId);
        this.items = itemsIds.stream().map(itemId -> entityManager.find(LineItem.class, itemId)).collect(Collectors.toSet());
    }

    public ShoppingCart() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
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
}