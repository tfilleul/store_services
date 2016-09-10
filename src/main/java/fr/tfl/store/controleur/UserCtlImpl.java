package fr.tfl.store.controleur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import fr.tfl.store.bean.User;
import fr.tfl.store.model.UserModel;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.IUserService;

@Controller
public class UserCtlImpl extends AbstractStoreCtlImpl {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserCtlImpl.class);
	
	public static final String ROOT = "C:\\Users\\t.filleul\\Downloads\\store\\";

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/user/picture/{idpicture}")	
	public @ResponseBody ResponseEntity<byte[]> getProfilPicture(@PathVariable("idpicture")String idPicture) throws IOException {
	  	File imgPath = new File(ROOT + idPicture);

	    byte[] image = Files.readAllBytes(imgPath.toPath());
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);	
	    headers.setContentLength(image.length);
	    return new ResponseEntity<byte[]>(image, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}")	
	public @ResponseBody String getUser(@PathVariable("id") Long id)  {
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
	
	@RequestMapping(value="/users")	
	public @ResponseBody String getAllUser() throws JsonProcessingException {
		logger.info("getAllUser");		
		ObjectWriter writer = filter();
        final List<User> list = userService.loadAllUser();
        return writer.writeValueAsString(list);	
	}	
	
	
	@RequestMapping(value="/user/search",method = RequestMethod.POST)	
	public @ResponseBody List<UserModel> getUserByCriteria(@RequestBody CritereImpl critere) {
		logger.info("getUserByCriteria");
		List<UserModel> list = (List<UserModel>)userService.userCriteria(critere); 
		return list;
	}
				
	@RequestMapping(value="/user/add",method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public @ResponseBody void addUserFile(@RequestParam("user") String userString, @RequestParam("file") MultipartFile file) throws IOException {
		logger.info("#####addUserFile");
		User user = new User();
		ObjectMapper mapper = new ObjectMapper();
		
		user = mapper.readValue(userString, User.class);
		if (!file.isEmpty()) {
		    UUID idPicture = UUID.randomUUID();
			Files.copy(file.getInputStream(), Paths.get(ROOT, idPicture.toString()));
			user.setIdpicture(idPicture.toString());
		}		
		userService.save(user);	
	}
	
	@RequestMapping(value="/user/add/json",method = RequestMethod.POST)
		public @ResponseBody void addUser(@RequestBody User user) throws JsonProcessingException {		
		logger.info("SearchUserImpl addUser");
		ObjectWriter writer = filter();
		userService.save(user);		
	}
				
	@RequestMapping(value="/user/put",method = RequestMethod.POST)
	public @ResponseBody void putUser(@RequestParam("user") String userString, @RequestParam("file") MultipartFile file) throws IOException {
		logger.info("/user/put" +userString);
		File filePicture = null;
		User user = new User();
		ObjectMapper mapper = new ObjectMapper();
		
		user = mapper.readValue(userString, User.class);		
		if(user.getIdpicture() != null) {
			filePicture = new File(ROOT + user.getIdpicture());
		}		
		if (!file.isEmpty()) {
			if (filePicture.exists()){
				filePicture.delete();
			}
			UUID idPicture = UUID.randomUUID();
			Files.copy(file.getInputStream(), Paths.get(ROOT, idPicture.toString()));
			user.setIdpicture(idPicture.toString());
		}
		userService.update(user);
		
	}
	
	
}
