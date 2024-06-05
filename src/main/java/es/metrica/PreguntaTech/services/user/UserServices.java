package es.metrica.PreguntaTech.services.user;

import es.metrica.PreguntaTech.model.LoginResult;
import es.metrica.PreguntaTech.model.User;

public interface UserServices {
	
	LoginResult login(User user);

	 User register(User user) ;
}
