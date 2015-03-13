package fr.tfl.store.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.model.UserModel;
import fr.tfl.store.persistance.IUserDao;
import fr.tfl.store.persistance.critere.CritereImpl;

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
	public UserModel auth(CredentialImpl credential) {			
		UserModel userModel = new UserModel();
		User user = userDao.auth(credential);
		userModel.copyModel(user);
		return userModel;
		
	}

}
