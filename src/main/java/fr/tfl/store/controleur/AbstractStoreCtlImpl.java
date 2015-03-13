package fr.tfl.store.controleur;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.tfl.store.util.ErrorResourceImpl;

public class AbstractStoreCtlImpl {
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResourceImpl handleException(ConstraintViolationException ex)
	{
		StringBuilder builder = new StringBuilder();		
	    Set<ConstraintViolation<?>> violations=ex.getConstraintViolations();
	    for (ConstraintViolation<?> constraintViolation : violations) {
	    	builder.append(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
		}
	    return new ErrorResourceImpl(HttpStatus.INTERNAL_SERVER_ERROR.value(), builder.toString());
	}

}
