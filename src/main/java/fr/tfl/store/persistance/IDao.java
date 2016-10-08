package fr.tfl.store.persistance;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import fr.tfl.store.persistance.critere.CritereImpl;

/**
 * Defines a generic data access object that is extended by all the DAOs in the
 * system. This interface defines the common CRUD methods like <code>save</code>
 * , <code>delete</code> <code>findAll</code> and <code>find</code>.
 */
public interface IDao<T extends IDomainEntity, ID extends Serializable> {

    // =============
    // = Attributs =
    // =============

    // ==============
    // = Constantes =
    // ==============

    // =================
    // = Constructeurs =
    // =================

    // ======================
    // = Methodes publiques =
    // ======================
	
	

    /**
     * Deletes the object that corresponds to the specified primary key (ID)
     * 
     * @param object
     *            the object to delete
     */
    <D extends T> void remove(D entity);

    /**
     * 
     * Get entities by criterai
     * 
     * @param dc
     * @param from
     * @param size
     * @return entities found
     */
    List<T> findByCriteria(DetachedCriteria dc, int from, int size);

    /**
     * Count entities by criteria
     * 
     * @param dc
     *            criteria
     * @return
     */
    int countByCriteria(DetachedCriteria dc);

    /**
     * Gets the object using specified primary key
     * 
     * @param id
     *            primary key (ID) of the object to retrieve
     * @return object corresponding to the specified primary key (ID)
     * @throws org.springframework.orm.ObjectRetrievalFailureException
     *             when object corresponding to the specified ID (primary key)
     *             is not found. This is a <code>RuntimeException</code>
     */
    T find(ID id);

    /**
     * Create the specified object.
     * 
     * @param object
     *            the object to create
     * @return managed copy of the original object. Use this object if you want
     *         the future changes managed (persisted).
     */
    <D extends T> D create(D object);

    /**
     * Update the specified object.
     * 
     * @param object
     *            the object to update
     * @return managed copy of the original object.
     */
    <D extends T> D update(D object);

    
    <D extends T> void save(D object); 
    
    /**
     * Remove the list of all objects of this type
     * 
     * @return value of the query execution
     */
    int removeAll();

    /**
     * Gets the list of all objects of this type
     * 
     * @return list of this objects; the list can be empty
     */
    List<T> findAll();

    /**
     * Count all objects of this type
     * 
     * @return number of entities
     */
    int countAll();

}
