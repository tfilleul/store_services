package fr.tfl.store.services;

import java.util.List;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.model.UserModel;
import fr.tfl.store.persistance.IUserDao;
import fr.tfl.store.persistance.critere.CritereImpl;

public interface IUserService {
	
	public void setUserDao(IUserDao userDao);
	
	public UserModel loadUser(int id);	
	
	public List<User> loadAllUser();
		
	public void save(User user);
	
	public User loadQueryUser(int id);
		
	public void update(User user);

	public List<UserModel> userCriteria(CritereImpl critere);

	
		
}
