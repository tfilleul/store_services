package fr.tfl.store.persistance;

import java.util.List;
import java.util.UUID;

import fr.tfl.store.bean.Product;
import fr.tfl.store.persistance.critere.CritereImpl;

public interface IProductDao extends IDao<Product,UUID> {

	List<Product> objectCriteria(CritereImpl critere);

}
	