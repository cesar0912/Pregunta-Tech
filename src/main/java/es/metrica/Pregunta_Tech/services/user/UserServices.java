package es.metrica.Pregunta_Tech.services.user;

import es.metrica.Pregunta_Tech.model.LoginResult;
import es.metrica.Pregunta_Tech.model.User;

public interface UserServices {
	
	LoginResult login(User user);

	 User register(User user) ;
}
