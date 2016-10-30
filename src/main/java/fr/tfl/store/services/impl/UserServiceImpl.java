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
import fr.tfl.store.model.UserDTO;
import fr.tfl.store.persistance.IUserDao;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.IStoreService;

/**
 * Couche service User
 * @author TFILLEUL
 *
 */
@Component("userService")
public class UserServiceImpl  implements IStoreService<User, Long> {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);		
	
//	@Autowired
//	private IUserDao userDao;
//	
//	@Autowired
//	public UserServiceImpl(IUserDao userDao) {
//		super(userDao);	
//	}
//
//	@Transactional(readOnly = true)
//	public List<User> objectCriteria(CritereImpl critere) {
//		
//		List<User> lstUsers = userDao.userCriteria(critere);		
//		logger.info("### userCriteria : " + critere);
//		return lstUsers;
//	}

}
