package fr.tfl.store.services.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.model.IStoreDTO;
import fr.tfl.store.persistance.IDao;
import fr.tfl.store.persistance.IDomainEntity;
import fr.tfl.store.services.IStoreService;

@Transactional
public abstract class AbstractServiceImpl<T extends IDomainEntity,ID extends Serializable> implements IStoreService<T,ID> {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);		
	
	private IDao<T,ID> storeDao;
	
	public AbstractServiceImpl(IDao<T, ID> storeDao) {
		super();
		this.storeDao = storeDao;	
	}
	
	@Transactional(readOnly = true)
	public T loadQueryObject(ID id) {
		T entity = storeDao.find(id);		
		return entity;
	}
	
	@Transactional(readOnly = true)
	public List<T> loadAllObjects() {
		// copy du model + regle de gestion		
		return storeDao.findAll();		
	}

	public void save(T entity) {
		storeDao.save(entity);		
	}

	public void update(T entity) {
		storeDao.update(entity);
	}
	

}
