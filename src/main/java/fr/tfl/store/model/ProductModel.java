package fr.tfl.store.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import fr.tfl.store.bean.Product;

@Component
public class ProductModel implements IStoreModel<Product>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private int version;
	
	public ProductModel copyModel(Product product) {		
		BeanUtils.copyProperties(product, this);
		//this.setProfil(profilModel);
		return this;
	}
	
	public List<ProductModel> copyModel(List<Product> products) {
		
		List<ProductModel> listModel = new ArrayList<ProductModel>();
		for (Product product : products) {	
			BeanUtils.copyProperties(product, this);
			listModel.add(this);
		}	
		return listModel;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
