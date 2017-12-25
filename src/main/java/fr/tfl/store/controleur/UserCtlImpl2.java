package fr.tfl.store.controleur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.tfl.store.model.UserDTO;;

@Path("/user")
@RequestScoped
public class UserCtlImpl2 extends AbstractStoreCtlImpl {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserCtlImpl2.class);

		
  //  @Inject	
	//@Qualifier("userServiceFacade")
 //	private IUserServiceFacade userServiceFacade;
	
	private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter writer = filter();

    @GET
    @Path("/user/picture/{idpicture}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("GES")
	//@RequestMapping(value = "/user/picture/{idpicture}")	
	public ResponseEntity<byte[]> getProfilPicture(@PathParam("idpicture")String idPicture) throws IOException {
	  	File imgPath = new File(ROOT + idPicture);

	    byte[] image = Files.readAllBytes(imgPath.toPath());
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);	
	    headers.setContentLength(image.length);
	    return new ResponseEntity<byte[]>(image, headers, HttpStatus.OK);
	}
	
	@GET
	@Path("/user/{id}")
	//@RequestMapping(value="/user/{id}")	
	public UserDTO getUser(@PathParam("id") Long id)  {
		logger.info("getUser");
        //final UserDTO user = (UserDTO)userServiceFacade.loadQueryObject(id);
		UserDTO user = new UserDTO();
		user.setFirstname("test");
        return user;				
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/users2")	
	//@RequestMapping(value="/users")	
	public List<UserDTO> getAllUser() throws JsonProcessingException {
		logger.info("getAllUser");
		List<UserDTO> listDto = new ArrayList<UserDTO>();
        //final List<UserDTO> list = userServiceFacade.loadAllObjects();
        return listDto;	
	}	
	
//	@RequestMapping(value="/user/search",method = RequestMethod.POST)	
//	public @ResponseBody List<UserDTO> getUserByCriteria(@RequestBody CritereImpl critere) {
//		logger.info("getUserByCriteria");
//		return userServiceFacade.getListByCriteria(critere);
//	}
//	
//	
//	@RequestMapping(value="/user/search/name",method = RequestMethod.POST)	
//	public @ResponseBody List<UserNameDTO> getUserNameByCriteria(@RequestBody CritereImpl critere) {
//		logger.info("getUserByCriteria");
//		return userServiceFacade.getListNameByCriteria(critere);
//	}
	
//	@Secured("admin")
//	@RequestMapping(value="/user/add",method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
//	public @ResponseBody void addUserFile(@RequestParam("user") String userString, @RequestParam("file") MultipartFile file) throws IOException {
//		logger.info("#####addUserFile");
//		UserDTO userDTO = new UserDTO();
//		userDTO = mapper.readValue(userString, UserDTO.class);
//		if (!file.isEmpty()) {
//		    UUID idPicture = UUID.randomUUID();
//			Files.copy(file.getInputStream(), Paths.get(ROOT, idPicture.toString()));
//			userDTO.setIdpicture(idPicture.toString());
//		}		
//		userServiceFacade.save(userDTO);	
//	}
	
//	@RequestMapping(value="/user/add/json",method = RequestMethod.POST)
//		public @ResponseBody void addUser(@RequestBody UserDTO userDto) throws JsonProcessingException {		
//		logger.info("SearchUserImpl addUser");
//        User user = modelMapper.map(userDto, User.class);
//		userService.save(user);		
//	}
	
//	@RolesAllowed("toto")
//    @Produces(MediaType.MULTIPART_FORM_DATA)
//	@POST
//	@Path("/user/put")
//	//@RequestMapping(value="/user/put",method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
//	public  void putUser(@QueryParam(value="file") MultipartFile file,@QueryParam("user") String userString) throws IOException {
//		logger.info("/user/put" +userString);
//						
//		File filePicture = null;
//		UserDTO user = new UserDTO();
//		user = mapper.readValue(userString, UserDTO.class);		
//		if(user.getIdpicture() != null) {
//			filePicture = new File(ROOT + user.getIdpicture());
//		}		
//		if (file != null && !file.isEmpty()) {
//			if (filePicture != null && filePicture.exists()){
//				filePicture.delete();
//			}
//			UUID idPicture = UUID.randomUUID();
//			Files.copy(file.getInputStream(), Paths.get(ROOT, idPicture.toString()));
//			user.setIdpicture(idPicture.toString());
//		}
//		//userServiceFacade.update(user);		
//	}
	
}
