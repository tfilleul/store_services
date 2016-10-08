package fr.tfl.store.persistance.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.tfl.store.bean.Product;
import fr.tfl.store.bean.User;
import fr.tfl.store.persistance.IProductDao;
import fr.tfl.store.persistance.critere.CritereImpl;

@Repository
public class ProductDaoImpl extends AbstractDao<Product, UUID> implements IProductDao {

	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(ProductDaoImpl.class);
	
	
	public ProductDaoImpl() {
		super(Product.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> objectCriteria(CritereImpl critere) {			
		final Session session = this.getSession();
		Criteria criteria = session.createCriteria(User.class);
		addRestrictionIfNotNull(criteria,"name",critere.getName());
		addRestrictionIfNotNull(criteria,"firstname",critere.getFirstName());
		addRestrictionIfNotNull(criteria,"age",critere.getAge());
		return criteria.list();
	}
	
	
}