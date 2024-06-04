package es.metrica.Pregunta_Tech;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.metrica.Pregunta_Tech.model.User;
import es.metrica.Pregunta_Tech.utils.hash.HashingUtil;
import es.metrica.Pregunta_Tech.utils.jwt.Jwt;

	@SpringBootTest
 	class securityTests {

		@Autowired
		HashingUtil hu;
		@Autowired
		HashingUtil hu2;
		
		@Test
		void contextHashTest(){
			assertNotNull(hu);
			assertNotNull(hu2);
		}
		
		@Test
		void hashingTests() {
			String originalPass = "1234";
			String pass1 = hu.hash(originalPass);
			String pass2 = hu2.hash("54321");
			
			Assertions.assertFalse(hu.verify(pass2, originalPass));
			Assertions.assertFalse(hu2.verify(pass2, originalPass));
			Assertions.assertTrue(hu2.verify(pass1, originalPass));
			Assertions.assertTrue(hu.verify(pass1, originalPass));
		}
		

		@Autowired
		Jwt jwt;
		
		@Test
		void jwtTest() {
			
			User user = new User();
			HashMap<String, Object> hmap = new HashMap<>();
			
			long id = 2345L;
			user.setName("RandomName");
			user.setId(id);
			user.setPassword("R4nd0mP4ssW0rd");
			hmap.put("pass", user.getPassword());
			
			String token = jwt.generateToken(user, hmap);
			
			Assertions.assertEquals(jwt.getUser(token), id);
			
		}
}
