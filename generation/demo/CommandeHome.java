package demo;
// Generated 22 ao�t 2016 09:44:22 by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Commande.
 * @see demo.Commande
 * @author Hibernate Tools
 */
@Stateless
public class CommandeHome {

	private static final Log log = LogFactory.getLog(CommandeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Commande transientInstance) {
		log.debug("persisting Commande instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Commande persistentInstance) {
		log.debug("removing Commande instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Commande merge(Commande detachedInstance) {
		log.debug("merging Commande instance");
		try {
			Commande result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Commande findById(Serializable id) {
		log.debug("getting Commande instance with id: " + id);
		try {
			Commande instance = entityManager.find(Commande.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
