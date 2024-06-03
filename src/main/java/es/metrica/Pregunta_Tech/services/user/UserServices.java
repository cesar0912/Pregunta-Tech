package es.metrica.Pregunta_Tech.services.user;

import es.metrica.Pregunta_Tech.model.User;

public interface UserServices {
	
	String login(User user);

	 User register(User user) ;
}
