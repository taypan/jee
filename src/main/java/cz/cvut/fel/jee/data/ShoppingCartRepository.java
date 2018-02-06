package cz.cvut.fel.jee.data;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.LineItem;
import cz.cvut.fel.jee.model.ShoppingCart;
import cz.cvut.fel.jee.model.ShoppingCart_;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ShoppingCartRepository {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<ShoppingCart> userEventSrc;

    public List<ShoppingCart> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ShoppingCart> criteria = cb.createQuery(ShoppingCart.class);
        Root<ShoppingCart> productRoot = criteria.from(ShoppingCart.class);
        criteria.select(productRoot);
        return em.createQuery(criteria).getResultList();
    }

    public ShoppingCart findById(Long id) {
        return em.find(ShoppingCart.class, id);
    }

    public ShoppingCart addItem(Account account, LineItem item) throws NamingException {
        ShoppingCart shoppingCart = findByAccount(account);
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart(account.getId(), new HashSet<>());
        }

        LineItem newItem = null;
        for(LineItem lineItem : shoppingCart.getItems()){
            if(lineItem.getId() == item.getId()){
                newItem = new LineItem(lineItem.getAmount() + item.getAmount(), lineItem.getId());
                shoppingCart.getItems().remove(lineItem);
            }
        }

        if(newItem != null){
            newItem = item;
        }

        shoppingCart.getItems().add(newItem);

        em.persist(shoppingCart);
        userEventSrc.fire(shoppingCart);

        return shoppingCart;
    }

    public ShoppingCart findByAccount(Account account){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ShoppingCart> criteria = builder.createQuery( ShoppingCart.class );
        Root<ShoppingCart> shoppingCartRoot = criteria.from( ShoppingCart.class );
        criteria.select( shoppingCartRoot );
        criteria.where( builder.equal( shoppingCartRoot.get( ShoppingCart_.account), account ) );
        return em.createQuery( criteria ).getSingleResult();
    }

}
