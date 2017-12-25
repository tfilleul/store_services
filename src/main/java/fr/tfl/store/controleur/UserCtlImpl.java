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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import fr.tfl.store.model.UserDTO;
import fr.tfl.store.model.UserNameDTO;
import fr.tfl.store.persistance.critere.CritereImpl;
import fr.tfl.store.services.facade.IUserServiceFacade;;

@Controller
public class UserCtlImpl extends AbstractStoreCtlImpl {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserCtlImpl.class);

	
	@Autowired
	@Qualifier("userServiceFacade")
	private IUserServiceFacade userServiceFacade;
	
	private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter writer = filter();

	
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
	public @ResponseBody UserDTO getUser(@PathVariable("id") Long id)  {
		logger.info("getUser");
        final UserDTO user = (UserDTO)userServiceFacade.loadQueryObject(id);
        return user;				
	}
	
	@RequestMapping(value="/user/users")	
	public @ResponseBody List<UserDTO> getAllUser() throws JsonProcessingException {
		logger.info("getAllUser");		
        final List<UserDTO> list = userServiceFacade.loadAllObjects();
        return list;	
	}	
	
	@RequestMapping(value="/user/search",method = RequestMethod.POST)	
	public @ResponseBody List<UserDTO> getUserByCriteria(@RequestBody CritereImpl critere) {
		logger.info("getUserByCriteria");
		return userServiceFacade.getListByCriteria(critere);
	}
	
	
	@RequestMapping(value="/user/search/name",method = RequestMethod.POST)	
	public @ResponseBody List<UserNameDTO> getUserNameByCriteria(@RequestBody CritereImpl critere) {
		logger.info("getUserByCriteria");
		return userServiceFacade.getListNameByCriteria(critere);
	}
	
	@Secured("admin")
	@RequestMapping(value="/user/add",method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public @ResponseBody void addUserFile(@RequestParam("user") String userString, @RequestParam("file") MultipartFile file) throws IOException {
		logger.info("#####addUserFile");
		UserDTO userDTO = new UserDTO();
		userDTO = mapper.readValue(userString, UserDTO.class);
		if (!file.isEmpty()) {
		    UUID idPicture = UUID.randomUUID();
			Files.copy(file.getInputStream(), Paths.get(ROOT, idPicture.toString()));
			userDTO.setIdpicture(idPicture.toString());
		}		
		userServiceFacade.save(userDTO);	
	}
	
//	@RequestMapping(value="/user/add/json",method = RequestMethod.POST)
//		public @ResponseBody void addUser(@RequestBody UserDTO userDto) throws JsonProcessingException {		
//		logger.info("SearchUserImpl addUser");
//        User user = modelMapper.map(userDto, User.class);
//		userService.save(user);		
//	}
	
	@RequestMapping(value="/user/put",method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public @ResponseBody void putUser(@RequestParam(value="file", required = false) MultipartFile file,@RequestParam("user") String userString) throws IOException {
		logger.info("/user/put" +userString);
						
		File filePicture = null;
		UserDTO user = new UserDTO();
		user = mapper.readValue(userString, UserDTO.class);		
		if(user.getIdpicture() != null) {
			filePicture = new File(ROOT + user.getIdpicture());
		}		
		if (file != null && !file.isEmpty()) {
			if (filePicture != null && filePicture.exists()){
				filePicture.delete();
			}
			UUID idPicture = UUID.randomUUID();
			Files.copy(file.getInputStream(), Paths.get(ROOT, idPicture.toString()));
			user.setIdpicture(idPicture.toString());
		}
		userServiceFacade.update(user);		
	}
	
}
