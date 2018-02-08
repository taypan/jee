package cz.cvut.fel.jee.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="shoppingcarts")
public class ShoppingCart implements Serializable, Identifiable {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "account_id")
//    @JsonProperty("account_id")
//    @JsonIdentityReference(alwaysAsId=true)
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private long account;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty("item_ids")
    @JsonIdentityReference(alwaysAsId=true)
    private List<LineItem> items = new ArrayList<>();

    public ShoppingCart(long account) {
        this.account = account;
        this.items = new ArrayList<>();
    }

    public ShoppingCart(long account, List<LineItem> items) {
        this.account = account;
        this.items = items;
    }

//    @JsonCreator
//    public ShoppingCart(
//            @JsonProperty("account_id") long accountId,
//            @JsonProperty("item_ids") Set<Long> itemsIds) throws NamingException {
//        final EntityManager entityManager = InitialContext.doLookup("java:/defaultEntityManager");
//        this.accountId = entityManager.find(Account.class, accountId);
//        this.accountId = entityManager.find(Account.class, accountId);
//        this.items = itemsIds.stream().map(itemId -> entityManager.find(LineItem.class, itemId)).collect(Collectors.toSet());
//    }

    public ShoppingCart() {
    }

}