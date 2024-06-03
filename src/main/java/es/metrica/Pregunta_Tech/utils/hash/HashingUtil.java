package es.metrica.Pregunta_Tech.utils.hash;

import org.springframework.beans.factory.annotation.Autowired;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class HashingUtil {
	@Autowired 
	Argon2 argon;
	
	
	public String hash(String pass) {
		argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		return  argon.hash(1, 1024, 1, pass);
	}
	
	public boolean verify(String pass, String pass2) {
		argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		return argon.verify(pass, pass2);
	}
	
}
