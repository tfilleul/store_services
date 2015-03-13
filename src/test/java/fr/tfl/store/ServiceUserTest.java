package fr.tfl.store;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tfl.store.bean.User;
import fr.tfl.store.services.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/persistance-context.xml")
public class ServiceUserTest extends TestCase {
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void testUserService() {
		int id = 1;
		User user =  (User)userService.loadQueryUser(id);
		System.out.println(user.getName());
		assertEquals("thierry", user.getFirstname());		
		assertEquals("filleul", user.getName());
	}
}
