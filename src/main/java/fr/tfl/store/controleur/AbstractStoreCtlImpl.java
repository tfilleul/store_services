package fr.tfl.store.controleur;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import fr.tfl.store.util.ErrorResourceImpl;

public class AbstractStoreCtlImpl {
	
	//protected static final String ROOT = "C:\\Users\\t.filleul\\Downloads\\store\\";
	protected static final String ROOT = "/tmp/data/";

	
	protected ObjectWriter filterAuth() {	
		final SimpleFilterProvider filter = new SimpleFilterProvider();
		final ObjectMapper mapper = new ObjectMapper();  
		filter.addFilter("ProfilFilter", SimpleBeanPropertyFilter.serializeAllExcept(""));
		filter.addFilter("AclStoreFilter", SimpleBeanPropertyFilter.serializeAllExcept("profil"));  
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);	
        return mapper.writer(filter);
	}
	
	/**
	 * 
	 * @return ObjectWriter
	 */
	protected ObjectWriter filter() {	
		final SimpleFilterProvider filter = new SimpleFilterProvider();
		final ObjectMapper mapper = new ObjectMapper();
        filter.addFilter("ProfilFilter", SimpleBeanPropertyFilter.serializeAllExcept("aclStores"));        
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);	
        return mapper.writer(filter);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	protected ErrorResourceImpl handleException(ConstraintViolationException ex)
	{
		StringBuilder builder = new StringBuilder();		
	    Set<ConstraintViolation<?>> violations=ex.getConstraintViolations();
	    for (ConstraintViolation<?> constraintViolation : violations) {
	    	builder.append(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
		}
	    return new ErrorResourceImpl(HttpStatus.INTERNAL_SERVER_ERROR.value(), builder.toString());
	}

}
