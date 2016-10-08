package fr.tfl.store.services.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.Product;
import fr.tfl.store.model.ProductModel;
import fr.tfl.store.persistance.IDao;
import fr.tfl.store.persistance.IProductDao;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.IStoreService;

/**
 * Couche service User
 * @author TFILLEUL
 *
 */
@Service("productService")
@Transactional
public class ProductServiceImpl extends AbstractServiceImpl<Product, ProductModel,UUID> implements IStoreService<Product, ProductModel,UUID> {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(ProductServiceImpl.class);		
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private ProductModel productModel;
	
	@Autowired
	public ProductServiceImpl(IProductDao productDao, ProductModel productModel) {
		super(productDao,productModel);	
	}

	@Transactional(readOnly = true)
	public List<ProductModel> objectCriteria(CritereImpl critere) {
		List<ProductModel> listModel = (List<ProductModel>)productModel.copyModel(productDao.objectCriteria(critere));		
		logger.info("### userCriteria : " + critere);
		return listModel;
	}

	public Product loadQueryObject(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
