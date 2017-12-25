package fr.tfl.store.services.facade;

import java.util.List;

import fr.tfl.store.model.UserDTO;
import fr.tfl.store.model.UserNameDTO;
import fr.tfl.store.persistance.critere.CritereImpl;

public interface IUserServiceFacade {
	
	  public List<UserNameDTO> getListNameByCriteria(CritereImpl critere);
	  
	  public List<UserDTO> loadAllObjects();
		
	  public void save(UserDTO store);
	 
	  public UserDTO loadQueryObject(Long id);
		
	  public void update(UserDTO store);
	  
	  public List<UserDTO> getListByCriteria(CritereImpl critere);



}
