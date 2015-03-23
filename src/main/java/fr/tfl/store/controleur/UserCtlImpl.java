package fr.tfl.store.controleur;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.tfl.store.bean.User;
import fr.tfl.store.model.UserModel;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.IUserService;

@Controller
public class UserCtlImpl extends AbstractStoreCtlImpl {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserCtlImpl.class);
	
	@Autowired
	private IUserService userService;

	
	@RequestMapping(value="/getUser")	
	public @ResponseBody String getUser(
			@RequestParam(value = "id",defaultValue="0") Long id)  {
		try {
			logger.info("getUser");
	        ObjectWriter writer = filter();
	        final User user = (User)userService.loadQueryUser(id);
	        return writer.writeValueAsString(user);
		}
		catch(JsonProcessingException ex) {
			logger.error(ex.getMessage());
			return "ko";
		}		
      //return (User)userService.loadQueryUser(id);
	}
	
	@RequestMapping(value="/getAllUser")	
	public @ResponseBody String getAllUser() throws JsonProcessingException {
		logger.info("getAllUser");		
		ObjectWriter writer = filter();
        final List<User> list = userService.loadAllUser();
        return writer.writeValueAsString(list);	
	}	
	
	
	@RequestMapping(value="/getUserByCriteria",method = RequestMethod.POST)	
	public @ResponseBody List<UserModel> getUserByCriteria(@RequestBody CritereImpl critere) {
		logger.info("getUserByCriteria");
		List<UserModel> list = (List<UserModel>)userService.userCriteria(critere); 
		return list;
	}
	
	@RequestMapping(value="/addUser",method = RequestMethod.POST)
	public @ResponseBody String addUser(@RequestBody User user){		
		logger.info("SearchUserImpl addUser");
		userService.save(user);
		return "ok";
	}
	
		
	@RequestMapping(value="/putUser",method = RequestMethod.PUT)
	public @ResponseBody String putUser(@RequestBody User user){
		logger.info("SearchUserImpl addUser");
		userService.update(user);
		return "ok";
	}
	
	
}
