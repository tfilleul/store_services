package fr.tfl.store.services.facade.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.model.IStoreDTO;
import fr.tfl.store.persistance.IDao;
import fr.tfl.store.persistance.IDomainEntity;
import fr.tfl.store.services.IStoreService;
import fr.tfl.store.services.facade.IServiceFacade;

@Transactional
public abstract class AbstractServiceFacade<D extends IStoreDTO,T extends IDomainEntity, ID extends Serializable>  implements IServiceFacade<D,ID>  {
	
		
	private IStoreService<T,ID> storeService;
	
	private IDao<T,ID> storeDao;
	
	public AbstractServiceFacade(IStoreService<T,ID> storeService,IDao<T,ID> storeDao) {
			this.storeService = storeService;	
			this.storeDao = storeDao;
	}

//	@Transactional(readOnly = true)
//	public List<D> loadAllObjects() {	
//		ModelMapper modelMapper = new ModelMapper();
//		Type listType = new TypeToken<List<IStoreDTO>>() {}.getType();
//		final List<T> list = storeDao.findAll();
//		List<D> lstDTO = modelMapper.map(list,listType);
//		return lstDTO;
//	}
//	
//	public void save(D storeDto) {		
//		ModelMapper modelMapper = new ModelMapper();
//        T user = (T)modelMapper.map(storeDto, IDomainEntity.class);
//        storeDao.save(user);		
//	}
//	
//	@Transactional(readOnly = true)
//	public D loadQueryObject(ID id) {
//		ModelMapper modelMapper = new ModelMapper();
//		//TypeToken<D> type = new TypeToken<D>(getClass()) {};
//        T store = (T)storeDao.find(id);
//        D storeDTO = (D)modelMapper.map(store, IStoreDTO.class);
//        return storeDTO;
//	}
//		
//	public void update(D storeDTO) {
//		ModelMapper modelMapper = new ModelMapper();
//        T user = (T)modelMapper.map(storeDTO, IDomainEntity.class);
//        storeDao.update(user);		
//	}

//	public List<D> getListByCriteria(CritereImpl critere) {		
//		Type listType = new TypeToken<List<IStoreDTO>>() {}.getType();
//		List<T> listUsers = (List<T>)storeDao.objectCriteria(critere);
//		List<D> lstDTO = modelMapper.map(listUsers,listType);
//		return lstDTO;		
//	}
}
