package fr.tfl.store.services.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.Product;
import fr.tfl.store.services.IStoreService;

/**
 * Couche service User
 * @author TFILLEUL
 *
 */

@Component("productService")
public class ProductServiceImpl implements IStoreService<Product, UUID> {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(ProductServiceImpl.class);		
	
//	@Autowired
//	private IProductDao productDao;
//	
//	
//	@Autowired
//	public ProductServiceImpl(IProductDao productDao) {
//		super(productDao);	
//	}
//
//	@Transactional(readOnly = true)
//	public List<Product> objectCriteria(CritereImpl critere) {
//		List<Product> listModel = productDao.productCriteria(critere);		
//		//List<ProductDTO> listModel = (List<ProductDTO>)ProductDTO.copyModel(productDao.objectCriteria(critere));		
//		logger.info("### userCriteria : " + critere);
//		return listModel;
//	}

}
