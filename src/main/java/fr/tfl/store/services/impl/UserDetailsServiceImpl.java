package fr.tfl.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.User;
import fr.tfl.store.persistance.IUserDao;
import fr.tfl.store.security.StoreDetailUser;


@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired	
  private IUserDao userDao;	
  
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String userName)
      throws UsernameNotFoundException, DataAccessException {
   
    User userEntity = userDao.findByLogin(userName);
    if (userEntity == null)
      throw new UsernameNotFoundException("user not found");

    return buildUserFromUserEntity(userEntity);
  }
  
  private StoreDetailUser buildUserFromUserEntity(User userEntity) {

	    String username = userEntity.getMail();
	    String password = userEntity.getPassword();
	    boolean enabled = true; 	    
	  	StoreDetailUser user = new StoreDetailUser(username, password, enabled);	      
	    return user;
	  }
  
}
