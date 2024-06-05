package es.metrica.Pregunta_Tech.utils.hash;

import org.springframework.stereotype.Component;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Component
public class HashingUtil {

	Argon2 argon;
	
	@SuppressWarnings("deprecation")
	public String hash(String pass) {
		argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id) ;
		return  argon.hash(1, 1024, 1, pass);
	}
	
	@SuppressWarnings("deprecation")
	public boolean verify(String hashedPass, String pass) {
		argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		return argon.verify(hashedPass, pass);
	}
	
}
