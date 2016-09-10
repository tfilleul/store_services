package demo;
// Generated 30 ao�t 2016 23:51:02 by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Utilisateur.
 * @see demo.Utilisateur
 * @author Hibernate Tools
 */
@Stateless
public class UtilisateurHome {

	private static final Log log = LogFactory.getLog(UtilisateurHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Utilisateur transientInstance) {
		log.debug("persisting Utilisateur instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Utilisateur persistentInstance) {
		log.debug("removing Utilisateur instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Utilisateur merge(Utilisateur detachedInstance) {
		log.debug("merging Utilisateur instance");
		try {
			Utilisateur result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Utilisateur findById(long id) {
		log.debug("getting Utilisateur instance with id: " + id);
		try {
			Utilisateur instance = entityManager.find(Utilisateur.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
