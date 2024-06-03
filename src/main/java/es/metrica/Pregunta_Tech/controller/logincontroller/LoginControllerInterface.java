package es.metrica.Pregunta_Tech.controller.logincontroller;

import es.metrica.Pregunta_Tech.model.LoginResult;
import es.metrica.Pregunta_Tech.model.User;

public interface LoginControllerInterface {

	LoginResult login(User user);
}
