package fr.tfl.store.services.facade;

import java.io.Serializable;
import java.util.List;

import fr.tfl.store.model.IStoreDTO;
import fr.tfl.store.persistance.critere.CritereImpl;

public interface IServiceFacade <D extends IStoreDTO,ID extends Serializable>{
	
  public List<D> loadAllObjects();
	
  public void save(D store);
 
  public D loadQueryObject(ID id);
	
  public void update(D store);
  
  public List<D> getListByCriteria(CritereImpl critere);
  


}
