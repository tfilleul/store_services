package fr.tfl.store.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.tfl.store.bean.AuthentificationBean;
import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;
import fr.tfl.store.services.AuthentificationServiceImpl;
import fr.tfl.store.services.IAuthService;

@Controller
public class AuthCtlImpl extends AbstractStoreCtlImpl {
	
		
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(AuthCtlImpl.class);
	
	@Autowired
	private IAuthService authService;
	
	@RequestMapping(value="/authUser",method = RequestMethod.PUT)
	public @ResponseBody String authUser(@RequestBody CredentialImpl credential,HttpServletRequest request) throws JsonProcessingException{
		logger.info("AuthUser");
		ObjectWriter writer = filterAuth();
		User user = authService.auth(credential);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return writer.writeValueAsString(user);
	}	
	
	@RequestMapping(value="/logon",method = RequestMethod.PUT)
	public @ResponseBody AuthentificationBean authUser(@RequestBody AuthentificationBean authBean,HttpServletRequest request) throws JsonProcessingException{
		logger.info("AuthUser");
		
		AuthentificationServiceImpl auth = new  AuthentificationServiceImpl();
		return auth.RFA_S20_logon(authBean);		
	}	
	
}
