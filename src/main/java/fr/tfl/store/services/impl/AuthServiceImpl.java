package fr.tfl.store.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.persistance.IUserDao;
import fr.tfl.store.services.IAuthService;

/**
 * Couche service User
 * @author TFILLEUL
 *
 */
@Service
@Transactional
public class AuthServiceImpl implements IAuthService {
	
	private IUserDao userDao;
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(AuthServiceImpl.class);
		
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Transactional(readOnly = true)
	public User auth(CredentialImpl credential) {
		// stockage des acl en session
		User user = userDao.auth(credential);	
		user.getProfil().getAclStores().size();
		return user;
		
	}

}
