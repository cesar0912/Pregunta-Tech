package es.metrica.Pregunta_Tech;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import es.metrica.Pregunta_Tech.utils.hash.HashingUtil;

	@SpringBootTest
 	class securityTests {

		@Autowired
		HashingUtil hu;
		@Autowired
		HashingUtil hu2;
		
		@Test
		void context(){
			
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
		
}
