package fr.tfl.store.controleur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.tfl.store.model.ProductDTO;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.facade.IServiceFacade;

@Controller
public class ProductCtlImpl extends AbstractStoreCtlImpl {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(ProductCtlImpl.class);
	
	
	@Autowired
	@Qualifier("productServiceFacade")
	private IServiceFacade<ProductDTO,UUID> productServiceFacade;
    
	private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter writer = filter();
	
	@RequestMapping(value="/product/{id}")	
	public @ResponseBody String getProduct(@PathVariable("id") UUID id)  {
		try {
			logger.info("getProduct");
	        ObjectWriter writer = filter();
	        final ProductDTO Product = productServiceFacade.loadQueryObject(id);
	        return writer.writeValueAsString(Product);
		}
		catch(JsonProcessingException ex) {
			logger.error(ex.getMessage());
			return "ko";
		}		
      //return (Product)ProductService.loadQueryProduct(id);
	}
	
	@RequestMapping(value="/product/products")	
	public @ResponseBody List<ProductDTO> getAllProduct()  {
		logger.info("getAllProduct");		
		ObjectWriter writer = filter();
        final List<ProductDTO> list = productServiceFacade.loadAllObjects();
        return list;	
	}	
	
	
	@RequestMapping(value="/product/search",method = RequestMethod.POST)	
	public @ResponseBody List<ProductDTO> getProductByCriteria(@RequestBody CritereImpl critere) {
		logger.info("getProductByCriteria");		
		List<ProductDTO> lstDTO = productServiceFacade.getListByCriteria(critere);		
		return lstDTO;
	}
	
	@Secured("admin")
	@RequestMapping(value="/product/add",method = RequestMethod.GET)
	public @ResponseBody void addUserFile(@RequestParam("user") String productString, @RequestParam("file") MultipartFile file) throws IOException {
		logger.info("#####addProductFile");
		ObjectMapper mapper = new ObjectMapper();
		
		ProductDTO productDTO = mapper.readValue(productString, ProductDTO.class);
		if (!file.isEmpty()) {
		    UUID idPicture = UUID.randomUUID();
			Files.copy(file.getInputStream(), Paths.get(ROOT, idPicture.toString()));
			productDTO.setIdpicture(idPicture.toString());
		}		
		productServiceFacade.save(productDTO);	
	}
	
	@Secured("admin")
	@RequestMapping(value="/product/add/cart",method = RequestMethod.POST)
	public @ResponseBody void addProductToCart(@RequestBody ProductDTO product,HttpServletRequest request) throws JsonProcessingException {		
		
		List<ProductDTO> listProducts = new ArrayList<ProductDTO>();
		HttpSession  sessionObj = request.getSession(true);
		logger.info("Add Cart" + sessionObj.getId());

		if (sessionObj.getAttribute("CART") != null) {
			listProducts= (List<ProductDTO>)sessionObj.getAttribute("CART"); 
		}
		listProducts.add(product);
		sessionObj.setAttribute("CART", listProducts);
	}
	
	@RequestMapping(value="/product/delete/cart/{id}",method = RequestMethod.DELETE)
	public @ResponseBody void delProductToCart(@PathVariable("id") UUID id,HttpServletRequest request) throws JsonProcessingException {		
		
		List<ProductDTO> listProducts = new ArrayList<ProductDTO>();
		HttpSession  sessionObj = request.getSession(true);
		logger.info("Del Cart" + sessionObj.getId());

		if (sessionObj.getAttribute("CART") != null) {
			listProducts= (List<ProductDTO>)sessionObj.getAttribute("CART"); 
		}
		for (Iterator iterator = listProducts.iterator(); iterator.hasNext();) {
			ProductDTO product = (ProductDTO) iterator.next();
			if (product.getId().equals(id)) {
				listProducts.remove(product);
				break;
			}			
		}
		sessionObj.setAttribute("CART", listProducts);
	}

	
	@RequestMapping(value="/product/get/cart",method = RequestMethod.GET)
	public @ResponseBody String getCart(HttpServletRequest request) throws JsonProcessingException {		
		
		List<ProductDTO> listProducts = new ArrayList<ProductDTO>();
		logger.info("get Cart");
		HttpSession  sessionObj = request.getSession();
		logger.info("Get Cart" + sessionObj.getId());

		ObjectWriter writer = filter();
		if (sessionObj.getAttribute("CART") != null) {
			listProducts= (List<ProductDTO>)sessionObj.getAttribute("CART"); 
		}
        return writer.writeValueAsString(listProducts);	
	}
		
	
//	@RequestMapping(value="/product/add",method = RequestMethod.POST)
//		public @ResponseBody void addProduct(@RequestBody Product product) throws JsonProcessingException {		
//		logger.info("AddProduct");
//		ObjectWriter writer = filter();
//		storeService.save(product);		
//	}
		
}
