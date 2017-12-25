package fr.tfl.store.services.facade.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.User;
import fr.tfl.store.model.UserDTO;
import fr.tfl.store.model.UserNameDTO;
import fr.tfl.store.persistance.IUserDao;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.IStoreService;
import fr.tfl.store.services.facade.IUserServiceFacade;

@Service("userServiceFacade")
@Transactional
public class UserServiceFacade implements IUserServiceFacade   {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceFacade.class);
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	@Qualifier("userService")
	private IStoreService<User,Long> userService;
	
	@Transactional(readOnly = true)
	public List<UserDTO> loadAllObjects() {	
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<UserDTO>>() {}.getType();
		final List<User> list = userDao.findAll();
		List<UserDTO> lstDTO = modelMapper.map(list,listType);
		return lstDTO;
	}
	
	public void save(UserDTO storeDto) {		
		ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(storeDto, User.class);
        userDao.save(user);		
	}
	
	@Transactional(readOnly = true)
	public UserDTO loadQueryObject(Long id) {
		ModelMapper modelMapper = new ModelMapper();
		//TypeToken<D> type = new TypeToken<D>(getClass()) {};
        User store = userDao.find(id);
        UserDTO storeDTO = modelMapper.map(store, UserDTO.class);
        return storeDTO;
	}
		
	public void update(UserDTO storeDTO) {
		ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(storeDTO, User.class);
        userDao.update(user);		
	}
	
	@Transactional(readOnly = true)
	public List<UserDTO> getListByCriteria(CritereImpl critere) {		
		logger.info("getListByCriteria : " + critere);
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<UserDTO>>() {}.getType();
		List<User> lstUsers = userDao.userCriteria(critere);		
		List<UserDTO> lstDTO = modelMapper.map(lstUsers,listType);
		return lstDTO;
	}
	
	@Transactional(readOnly = true)
	public List<UserNameDTO> getListNameByCriteria(CritereImpl critere) {		
		logger.info("userCriteriaListName : " + critere);
		List<UserNameDTO> lstDTO = new ArrayList<UserNameDTO>();
		List<Object[]> lstUsers = userDao.userCriteriaListName(critere);
		for (Iterator<Object[]> iterator = lstUsers.iterator(); iterator.hasNext();) {
			Object[] myResult  = (Object[]) iterator.next();
			UserNameDTO userNameDTO = new UserNameDTO((Long)myResult[0],(String)myResult[1],(Integer)myResult[2]);
			lstDTO.add(userNameDTO);
		}
		return lstDTO;
	}
			
}
