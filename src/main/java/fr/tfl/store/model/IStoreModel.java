package fr.tfl.store.model;

import java.util.List;

import fr.tfl.store.persistance.IDomainEntity;

public interface IStoreModel<T extends IDomainEntity> {
	
	public IStoreModel<T> copyModel(T entity);

	public List<? extends IStoreModel<T>> copyModel(List<T> entities);


}
