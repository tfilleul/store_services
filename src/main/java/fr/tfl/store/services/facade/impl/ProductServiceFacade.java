package fr.tfl.store.services.facade.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.Product;
import fr.tfl.store.model.ProductDTO;
import fr.tfl.store.persistance.IProductDao;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.IStoreService;
import fr.tfl.store.services.impl.ProductServiceImpl;

@Service("productServiceFacade")
@Transactional
public class ProductServiceFacade extends AbstractServiceFacade<ProductDTO,Product,UUID>  {
	
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(ProductServiceImpl.class);	
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	@Qualifier("productService")
	private IStoreService<Product,UUID> productService;
	
	@Autowired
	public ProductServiceFacade(IStoreService<Product,UUID> productService,IProductDao productDao) {
		super(productService,productDao);
	}
	
	@Transactional(readOnly = true)
	public List<ProductDTO> loadAllObjects() {	
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<ProductDTO>>() {}.getType();
		final List<Product> list = productDao.findAll();
		List<ProductDTO> lstDTO = modelMapper.map(list,listType);
		return lstDTO;
	}
	
	public void save(ProductDTO storeDto) {		
		ModelMapper modelMapper = new ModelMapper();
        Product user = modelMapper.map(storeDto, Product.class);
        productDao.save(user);		
	}
	
	@Transactional(readOnly = true)
	public ProductDTO loadQueryObject(UUID id) {
		ModelMapper modelMapper = new ModelMapper();
        Product store = productDao.find(id);
        ProductDTO storeDTO = modelMapper.map(store, ProductDTO.class);
        return storeDTO;
	}
		
	public void update(ProductDTO storeDTO) {
		ModelMapper modelMapper = new ModelMapper();
        Product user = modelMapper.map(storeDTO, Product.class);
        productDao.update(user);		
	}
		
	@Transactional(readOnly = true)
	public List<ProductDTO> getListByCriteria(CritereImpl critere) {		
		logger.info("getListByCriteria : " + critere);
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<ProductDTO>>() {}.getType();
		List<Product> lstProducts = productDao.productCriteria(critere);		
		List<ProductDTO> lstDTO = modelMapper.map(lstProducts,listType);
		return lstDTO;
	}

}
