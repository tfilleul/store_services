package fr.tfl.store.persistance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.tfl.store.bean.Profil;
import fr.tfl.store.bean.User;
import fr.tfl.store.controleur.UserCtlImpl;
import fr.tfl.store.persistance.critere.CritereImpl;

public class RefDaoImpl extends HibernateDaoSupport {

	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(RefDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")
	public List<Profil> findAllRef() {		
		final Session session = this.getSessionFactory().getCurrentSession();		
		return  session.createCriteria(Profil.class).list();
	}	
}