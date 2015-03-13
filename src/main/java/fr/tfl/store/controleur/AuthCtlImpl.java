package fr.tfl.store.controleur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.model.UserModel;
import fr.tfl.store.services.IAuthService;

@Controller
public class AuthCtlImpl extends AbstractStoreCtlImpl {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(AuthCtlImpl.class);
	
	@Autowired
	private IAuthService authService;
	
	@RequestMapping(value="/authUser",method = RequestMethod.PUT)
	public @ResponseBody UserModel authUser(@RequestBody CredentialImpl credential){
		logger.info("AuthUser");
		return authService.auth(credential);
	}
		
}
