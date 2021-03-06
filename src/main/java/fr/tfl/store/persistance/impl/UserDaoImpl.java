package fr.tfl.store.persistance.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.controleur.UserCtlImpl;
import fr.tfl.store.persistance.IUserDao;
import fr.tfl.store.persistance.critere.CritereImpl;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements IUserDao {

	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserCtlImpl.class);
	
	
	public UserDaoImpl() {
		super(User.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> userCriteriaListName(CritereImpl critere) {			
		final Session session = this.getSession();
		Criteria criteria = session.createCriteria(User.class);
		addRestrictionIfNotNullAndLike(criteria,"name",critere.getName());
		addRestrictionIfNotNull(criteria,"firstname",critere.getFirstName());
		addRestrictionIfNotNull(criteria,"age",critere.getAge());
		criteria.setProjection(Projections.projectionList().add(Projections.property("id"))
				.add(Projections.property("name"))
				.add(Projections.property("version")));
		//.setResultTransformer(Transformers.aliasToBean(User.class));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> userCriteria(CritereImpl critere) {			
		final Session session = this.getSession();
		Criteria criteria = session.createCriteria(User.class);
		addRestrictionIfNotNull(criteria,"name",critere.getName());
		addRestrictionIfNotNull(criteria,"firstname",critere.getFirstName());
		addRestrictionIfNotNull(criteria,"age",critere.getAge());
		addRestrictionIfNotNull(criteria,"mail",critere.getEmail());
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public User findByLogin(String login) {			
		final Session session = this.getSession();
		Criteria  criteria = session.createCriteria(User.class)
		.add(Restrictions.eq("mail", login));			
		return (User)criteria.uniqueResult();
	}	
	
	@SuppressWarnings("unchecked")
	public User queryUser(int id) {
		User user = null;
		final String QUERY_USERS_BY_CRIT = "from User as user join fetch user.profil profil where user.id = :id";		
		final Session session = this.getSession();					
		user = (User)session.createQuery(QUERY_USERS_BY_CRIT)
					.setParameter("id",Integer.valueOf(id)).uniqueResult();		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public User auth(CredentialImpl credential) {
		List<User> listUser = null;
		User user = null;
		final String QUERY_USERS_BY_CRIT = "from User where mail=:login and password=:password";		
		final Session session = this.getSession();
		
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