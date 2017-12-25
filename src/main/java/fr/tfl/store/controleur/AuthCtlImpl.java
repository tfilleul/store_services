package fr.tfl.store.controleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.tfl.store.model.UserDTO;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.facade.IUserServiceFacade;


@Controller
public class AuthCtlImpl extends AbstractStoreCtlImpl {
	
		
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(AuthCtlImpl.class);
	
	@Autowired
	@Qualifier("userServiceFacade")
	private IUserServiceFacade userServiceFacade;

	
	@RequestMapping(value="/logon",method = RequestMethod.POST)
	public @ResponseBody List<UserDTO> authUser(@RequestBody CritereImpl critere,HttpServletRequest httpRequest) throws JsonProcessingException{
		logger.info("logon");
//		String userName = null;		
//		AccessToken token = null;
//		AuthentificationBean authBean = new AuthentificationBean();	
//		
//		KeycloakPrincipal<RefreshableKeycloakSecurityContext> kc =   (KeycloakPrincipal<RefreshableKeycloakSecurityContext>)httpRequest.getUserPrincipal();
//		if (kc != null) {
//			token = kc.getKeycloakSecurityContext().getToken();
//		}
//		authBean.setLastName(token.getName());
//		return authBean;
		final List<UserDTO> lstDTO =  userServiceFacade.getListByCriteria(critere);
        return lstDTO;	
	}
	
	@RequestMapping(value="/logoff")
	public @ResponseBody String logoff() throws JsonProcessingException{
		logger.info("logoff");			
		return "{\"id\":\"LOGOFF\"}";
		
	}
	
}
