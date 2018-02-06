package cz.cvut.fel.jee.service;

import cz.cvut.fel.jee.model.Account;
import cz.cvut.fel.jee.model.Identifiable;

import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author Vaclav Rechtberger
 */

public class GenericService<T extends Serializable & Identifiable> {
    private Class< T > clazz;

    protected EntityManager entityManager;

    public GenericService(Class<T> clazz, EntityManager entityManager){
        this.clazz = clazz;
        this.entityManager = entityManager;
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root);
        return entityManager.createQuery(criteria).getResultList();
    }

    public T findById(Long id){
        return entityManager.find(clazz, id);
    }

    public void update(T entity) throws Exception {
        //T entityOrig = entityManager.find(clazz,entity.getId());
        entityManager.merge(entity);
        //parent2.setName(parent.getName());
        //parent2.setChild(parent.getChild());
    }

    public void delete(T entity){
        entityManager.remove(entity);
    }
}
