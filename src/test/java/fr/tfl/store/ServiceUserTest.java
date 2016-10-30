package fr.tfl.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tfl.store.model.UserDTO;
import fr.tfl.store.services.facade.impl.UserServiceFacade;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/persistance-context.xml")
public class ServiceUserTest extends TestCase {
	
	@Autowired
	private UserServiceFacade userServiceFace;
	
	@Test
	public void testUserService() {
		Long id = 1L;
		UserDTO user =  userServiceFace.loadQueryObject(id);
		System.out.println(user.getName());
		assertEquals("thierry", user.getFirstname());		
		assertEquals("filleul", user.getName());
	}
}
