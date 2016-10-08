package fr.tfl.store.persistance.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.tfl.store.persistance.IDao;
import fr.tfl.store.persistance.IDomainEntity;

@Repository
public abstract class AbstractDao<T extends IDomainEntity, ID extends Serializable>
        implements IDao<T, ID> {

    // =============
    // = Attributs =
    // =============

    /** class logger */
	private static final Logger logger = LoggerFactory
			.getLogger(AbstractDao.class);
    /** Class name */
    protected final String cname = this.getClass().getName();
    /** Persistent class */
    private Class<T> domainClass;
    /** Entity Manager */
    
    @Autowired
    private transient SessionFactory sessionFactory; 
   

    // =================
    // = Constructeurs =
    // =================

    public AbstractDao() {
        this(null);
    }

    public AbstractDao(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    
    // ======================
    // = Methodes publiques =
    // ======================
    
    protected Session getSession() {
    	return sessionFactory.getCurrentSession();
    }

    /**
     * 
     * Get entities by criterai
     * 
     * @param dc
     * @param from
     * @param size
     * @return entities found
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(DetachedCriteria dc, int from, int size) {
        String mname = "remove";
       // logger.info(cname, mname, dc, from, size);
        try {
            Criteria criteria = dc.getExecutableCriteria(getSession());
            criteria.setFirstResult(from);
            criteria.setMaxResults(size);
            List<T> result = criteria.list();
           // logger.debug(result);
            return result;
        } catch (HibernateException e) {
            throw e;
        }

    }

    /**
     * Count entities by criteria
     * 
     * @param dc
     *            criteria
     * @return
     */
    public int countByCriteria(DetachedCriteria dc) {
        String mname = "remove";
       // logger.entry(cname, mname, dc);
        try {
            Criteria criteria = dc.getExecutableCriteria(getSession());
            criteria.setProjection(Projections.rowCount());
            Object o = criteria.list().get(0);
            int result = ((Long) o).intValue();
           // logger.exit(result);
            return result;
        } catch (HibernateException e) {
            throw e;
        }
    }

    /**
     * Delete an entity
     * 
     * @param o
     *            entity to delete
     */
    public <D extends T> void remove(D entity) {
        String mname = "remove";
       // logger.entry(cname, mname, entity);
        getSession().delete(entity);
       // logger.exit();
    }

    /**
     * 
     * Remove all.
     * 
     * @return number of entities removed
     */
    public int removeAll() {
        final String mname = "removeAll";
       // logger.entry(cname, mname);
        int result =  getSession().createQuery(
                "DELETE FROM " + domainClass.getName() + " h").executeUpdate();

       // logger.exit(result);
        return result;
    }

    /**
     * 
     * Load an entity
     * 
     * @param id
     *            primary of the entity
     * @return Entity loaded
     */
    public T find(ID id) {
        String mname = "find";
       // logger.entry(cname, mname, id);
        T result = null;
        result = (T)  getSession().get(domainClass, id);
       // logger.exit(result);
        return result;
    }
   

    /**
     * 
     * Update an entity
     * 
     * @param object
     *            Entity to be updated
     * @return Entity loaded
     */
    public <D extends T> D update(D object) {
        String mname = "update";
       // logger.entry(cname, mname, object);
        D result = (D)  getSession().merge(object);
       // logger.exit(result);
        return result;
    }
    
    /**
     * 
     * Update an entity
     * 
     * @param object
     *            Entity to be updated
     * @return Entity loaded
     */
    public <D extends T> void save(D object) {
        String mname = "update";
       // logger.entry(cname, mname, object);
       getSession().persist(object);
       // logger.exit(result);        
    }

    /**
     * 
     * Create an entity
     * 
     * @param object
     *            Entity to be updated
     * @return Entity loaded
     */
    public <D extends T> D create(D object) {
        String mname = "create";
       // logger.entry(cname, mname, object);
        D result = null;
        getSession().persist(object);
        result = object;
       // logger.exit(result);
        return result;
    }

    /**
     * 
     * Find all entity
     * 
     * @return All the Entities
     */
    @SuppressWarnings(value = "unchecked")
    public List<T> findAll() {
        String mname = "findAll";
       // logger.entry(cname, mname);
        List<T> result = null;

        result = getSession().createQuery("from " + domainClass.getSimpleName())
                .list();
       // logger.info(domainClass.getSimpleName() + " " + result.size()
       //         + " found!");
       // logger.exit(result);

        return result;

    }

    /**
     * 
     * Count all.
     * 
     * @return number of entities
     */
    public int countAll() {
        String mname = "countAll";
       // logger.info(cname, mname);
//        TypedQuery<Long> query =  getSession().createQuery("SELECT COUNT(c) FROM "
//                + domainClass.getName() + " c", Long.class);
 //       int result = (query.getSingleResult()).intValue();
       // logger.exit(result);
        return 0;
    }
  

    // =======================
    // = Getters and Setters =
    // =======================
    public Class<T> getDomainClass() {
        return domainClass;
    }

    public void setDomainClass(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    public String getCname() {
        return cname;
    }
    
    /**
	 * 
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	protected void addRestrictionIfNotNull(Criteria criteria, String propertyName, Object value) {
	    if (value != null) {
	        criteria.add(Restrictions.eq(propertyName, value));
	    }
	}
}