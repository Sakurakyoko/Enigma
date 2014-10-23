/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.ejb;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * This class was generated by Netbeans, and edited a bit afterwards.
 * It is an abstraction of object persistence to a database table.
 * It provides methods for inserting objects into a table row, updating
 * the table rows, deleting rows, getting all objects in the table, and
 * search by binary key.
 *
 * Subclasses of this class will be able to handle database operations
 * for a single class (the class that you substitute for T.
 *
 * @author evenal
 */
public abstract class AbstractPersister<T> {
    private Class<T> entityClass;

    public AbstractPersister(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    /** Insert the object entity into the table (in a new row).
     * Will fail if there already is a row with the same primary key */
    public T insert(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    /** Update a table row, by merging changes from entity into the
     * table row corresponding to entity */
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    /** Delete the table row where entity is stored */
    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /** Returns the object with primary key id */
    public T find(Object id) {
System.out.println("find(" + id + ")");
        return getEntityManager().find(entityClass, id);
    }

    /** Returns a list of all objects stored in the table */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Returns a list with a subset of the objects in the table. The range
     * parameter specifies the indices of the first and last object to get
     * from the table. This can be used to display large data sets page
     * by page.
     *
     * JPQL can't be used to formulate the query here, because this class is
     * generic. The type parameter T can be any class, and we do not know the
     * actual class name of T. The criteria api is used instead of JPQL
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /** Return the rowcount of the table */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
