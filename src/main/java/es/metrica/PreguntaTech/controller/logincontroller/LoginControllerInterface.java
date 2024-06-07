package es.metrica.PreguntaTech.controller.logincontroller;

import es.metrica.PreguntaTech.model.LoginResult;
import es.metrica.PreguntaTech.model.User;

public interface LoginControllerInterface {

	LoginResult login(User user);
}
