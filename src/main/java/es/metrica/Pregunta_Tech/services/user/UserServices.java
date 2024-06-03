package es.metrica.Pregunta_Tech.services.user;

import es.metrica.Pregunta_Tech.model.User;

public interface UserServices {
	
	String login(String email,String password);

	 User register(User user) ;
}
