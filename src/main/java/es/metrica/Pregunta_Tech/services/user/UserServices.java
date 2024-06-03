package es.metrica.Pregunta_Tech.services.user;

import es.metrica.Pregunta_Tech.model.User;

public interface UserServices {
	
	String login(User user);

	 User register(String email,String password,String name,String surname) ;
}
