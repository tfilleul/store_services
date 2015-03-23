package fr.tfl.store.persistance;

import java.util.List;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.persistance.critere.CritereImpl;

public interface IUserDao extends IDao<User,Long> {	
	
	public List<User> userCriteria(CritereImpl critere);

	public User auth(CredentialImpl credential);
		
	public User queryUser(int id);
	
	public User findByLogin(String login);

}
