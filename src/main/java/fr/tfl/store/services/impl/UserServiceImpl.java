package fr.tfl.store.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.model.UserModel;
import fr.tfl.store.persistance.IUserDao;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.IStoreService;

/**
 * Couche service User
 * @author TFILLEUL
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User, UserModel,Long> implements IStoreService<User, UserModel,Long> {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);		
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private UserModel userModel;
	
		
//	@Autowired
//	public UserServiceImpl(User entity, UserModel model, Long id) {
//		super(entity, model, id);
//	}	
	
	@Autowired
	public UserServiceImpl(IUserDao userDao, UserModel userModel) {
		super(userDao,userModel);	
	}

	@Transactional(readOnly = true)
	public UserModel auth(CredentialImpl credential) {			
		UserModel userModel = new UserModel();
		User user = userDao.auth(credential);
		userModel.copyModel(user);
		return userModel;		
	}

	@Transactional(readOnly = true)
	public List<UserModel> objectCriteria(CritereImpl critere) {
		List<UserModel> listModel = (List<UserModel>)userModel.copyModel(userDao.userCriteria(critere));		
		logger.info("### userCriteria : " + critere);
		return listModel;
	}

}
