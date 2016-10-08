package demo;
// Generated 30 ao�t 2016 23:51:02 by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Produit.
 * @see demo.Produit
 * @author Hibernate Tools
 */
@Stateless
public class ProduitHome {

	private static final Log log = LogFactory.getLog(ProduitHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Produit transientInstance) {
		log.debug("persisting Produit instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Produit persistentInstance) {
		log.debug("removing Produit instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Produit merge(Produit detachedInstance) {
		log.debug("merging Produit instance");
		try {
			Produit result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Produit findById(Serializable id) {
		log.debug("getting Produit instance with id: " + id);
		try {
			Produit instance = entityManager.find(Produit.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
