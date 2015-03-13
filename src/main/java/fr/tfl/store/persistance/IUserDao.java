package fr.tfl.store.persistance;

import java.util.List;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.persistance.critere.CritereImpl;

public interface IUserDao {
	
	public void save(User user);
	
	public void update(User user);
	
	public List<User> userCriteria(CritereImpl critere);

	public User auth(CredentialImpl credential);
	
	public User findUser(int id);

	public List<User> findAllUser();
	
	public User queryUser(int id);

}
