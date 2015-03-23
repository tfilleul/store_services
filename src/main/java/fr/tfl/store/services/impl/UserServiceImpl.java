package fr.tfl.store.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.model.UserModel;
import fr.tfl.store.persistance.IUserDao;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.IUserService;

/**
 * Couche service User
 * @author TFILLEUL
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);		
	
	@Transactional(readOnly = true)
	public UserModel loadUser(Long id) {
		// copy du model + regle de gestion
		UserModel userModel = new UserModel();
		userModel.copyModel(userDao.find(id));
		return userModel;
	}
	
	@Transactional(readOnly = true)
	public User loadQueryUser(Long id) {
		// copy du model + regle de gestion		
		//User user = userDao.queryUser(id);
		User user = userDao.find(id);		
		return user;
	}
	
//	@Transactional(readOnly = true)
//	public List<UserModel> loadAllUser() {
//		// copy du model + regle de gestion
//		UserModel userM = new UserModel();
//		List<UserModel> listModel = userM.copyModel(userDao.findAllUser());
//		return listModel;
//	}
	
	@Transactional(readOnly = true)
	public List<User> loadAllUser() {
		// copy du model + regle de gestion		
		return userDao.findAll();		
	}

	
	public void save(User user) {
		userDao.save(user);		
	}

	
	public void update(User user) {
		userDao.update(user);
	}

	@Transactional(readOnly = true)
	public List<UserModel> userCriteria(CritereImpl critere) {			
		UserModel userM = new UserModel();
		List<UserModel> listModel = userM.copyModel(userDao.userCriteria(critere));		
		logger.info("### userCriteria : " + critere);
		return listModel;
	}
	
	@Transactional(readOnly = true)
	public UserModel auth(CredentialImpl credential) {			
		UserModel userModel = new UserModel();
		User user = userDao.auth(credential);
		userModel.copyModel(user);
		return userModel;
		
	}

}
