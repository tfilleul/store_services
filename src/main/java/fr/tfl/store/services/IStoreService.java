package fr.tfl.store.services;

import java.io.Serializable;
import java.util.List;

import fr.tfl.store.model.IStoreModel;
import fr.tfl.store.persistance.IDomainEntity;
import fr.tfl.store.persistance.critere.CritereImpl;

public interface IStoreService<T extends IDomainEntity,P extends IStoreModel,ID extends Serializable> {
	
	public IStoreModel loadStore(ID id);
	
	public List<T> loadAllObjects();
		
	public void save(T store);
	
	public T loadQueryObject(ID id);
		
	public void update(T store);

	public List<P> objectCriteria(CritereImpl critere);

}
