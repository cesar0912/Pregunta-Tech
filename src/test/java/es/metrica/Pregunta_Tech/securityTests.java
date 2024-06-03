package es.metrica.Pregunta_Tech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.mkammerer.argon2.Argon2;
import es.metrica.Pregunta_Tech.utils.hash.HashingUtil;

 	class securityTests {
		@Autowired
		Argon2 argon;
		@Autowired
		HashingUtil hu;
		@Autowired
		HashingUtil hu2;
		
		@Test
		void hashingTests() {
			String pass1 = hu.hash("1234");
			String pass2 = hu2.hash("54321");
			
			Assertions.assertFalse(hu.verify(pass1, pass2));
			Assertions.assertFalse(hu2.verify(pass1, pass2));
			Assertions.assertTrue(hu2.verify(pass1, pass1));
			pass1 = pass2;
			Assertions.assertTrue(hu.verify(pass1, pass2));
			
		}
}
