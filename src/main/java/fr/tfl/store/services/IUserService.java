package fr.tfl.store.services;

import java.util.List;

import fr.tfl.store.bean.User;
import fr.tfl.store.model.UserModel;
import fr.tfl.store.persistance.critere.CritereImpl;

public interface IUserService {
	
	public UserModel loadUser(Long id);	
	
	public List<User> loadAllUser();
		
	public void save(User user);
	
	public User loadQueryUser(Long id);
		
	public void update(User user);

	public List<UserModel> userCriteria(CritereImpl critere);

	
		
}
