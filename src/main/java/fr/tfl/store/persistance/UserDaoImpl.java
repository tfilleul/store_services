package fr.tfl.store.persistance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.controleur.UserCtlImpl;
import fr.tfl.store.persistance.critere.CritereImpl;

public class UserDaoImpl extends HibernateDaoSupport implements IUserDao {

	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserCtlImpl.class);
	
	public void save(User user) {
		Session sess = this.getSessionFactory().getCurrentSession();
		sess.save(user);
	}
		
	public void update(User user) {
		Session sess = this.getSessionFactory().getCurrentSession();
		sess.merge(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> userCriteria(CritereImpl critere) {			
		final Session session = this.getSessionFactory().getCurrentSession();
		Criteria  criteria = session.createCriteria(User.class).add(Restrictions.eq("name", critere.getName()));		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public User findUser(int id) {
		User user = null;		
		final Session session = this.getSessionFactory().getCurrentSession();		
		user  = (User)session.get(User.class, id);			
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUser() {		
		final Session session = this.getSessionFactory().getCurrentSession();		
		return  session.createCriteria(User.class).list();		
		
	}
	
	@SuppressWarnings("unchecked")
	public User queryUser(int id) {
		User user = null;
		final String QUERY_USERS_BY_CRIT = "from User as user join fetch user.profil profil where user.id = :id";		
		final Session session = this.getSessionFactory().getCurrentSession();					
		user = (User)session.createQuery(QUERY_USERS_BY_CRIT)
					.setParameter("id",Integer.valueOf(id)).uniqueResult();		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public User auth(CredentialImpl credential) {
		List<User> listUser = null;
		User user = null;
		final String QUERY_USERS_BY_CRIT = "from User where mail=:login and password=:password";		
		final Session session = this.getSessionFactory().getCurrentSession();
		
		listUser = session.createQuery(QUERY_USERS_BY_CRIT)
					.setParameter("login", credential.getLogin())
					.setParameter("password", credential.getPassword())
					.list();
		if(listUser.size() > 0) {
			user = listUser.get(0);
		}
		return user;
	}	
}