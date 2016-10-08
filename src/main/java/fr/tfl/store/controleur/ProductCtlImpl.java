package fr.tfl.store.controleur;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.tfl.store.bean.Product;
import fr.tfl.store.model.ProductModel;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.IStoreService;

@Controller
public class ProductCtlImpl extends AbstractStoreCtlImpl {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(ProductCtlImpl.class);
	
	@Autowired
	@Qualifier("productService")
	private IStoreService<Product,ProductModel,UUID> storeService;
			
	@RequestMapping(value="/product/{id}")	
	public @ResponseBody String getProduct(@PathVariable("id") UUID id)  {
		try {
			logger.info("getProduct");
	        ObjectWriter writer = filter();
	        final Product Product = (Product)storeService.loadQueryObject(id);
	        return writer.writeValueAsString(Product);
		}
		catch(JsonProcessingException ex) {
			logger.error(ex.getMessage());
			return "ko";
		}		
      //return (Product)ProductService.loadQueryProduct(id);
	}
	
	@RequestMapping(value="/products")	
	public @ResponseBody String getAllProduct() throws JsonProcessingException {
		logger.info("getAllProduct");		
		ObjectWriter writer = filter();
        final List<Product> list = storeService.loadAllObjects();
        return writer.writeValueAsString(list);	
	}	
	
	
	@RequestMapping(value="/product/search",method = RequestMethod.POST)	
	public @ResponseBody List<ProductModel> getProductByCriteria(@RequestBody CritereImpl critere) {
		logger.info("getProductByCriteria");
		List<ProductModel> list = (List<ProductModel>)storeService.objectCriteria(critere); 
		return list;
	}
				
	
	@RequestMapping(value="/product/add",method = RequestMethod.POST)
		public @ResponseBody void addProduct(@RequestBody Product product) throws JsonProcessingException {		
		logger.info("AddProduct");
		ObjectWriter writer = filter();
		storeService.save(product);		
	}
	
	
}
