package fr.tfl.store.services;

import java.io.Serializable;
import java.util.List;

import fr.tfl.store.model.IStoreDTO;
import fr.tfl.store.persistance.IDomainEntity;
import fr.tfl.store.persistance.critere.CritereImpl;

public interface IStoreService<T extends IDomainEntity,ID extends Serializable> {
	

//	public List<T> loadAllObjects();
//		
//	public void save(T store);
//	
//	public T loadQueryObject(ID id);
//		
//	public void update(T store);
//
//	public List<T> objectCriteria(CritereImpl critere);

}
