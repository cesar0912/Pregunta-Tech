package es.metrica.PreguntaTech.controller.logincontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.metrica.PreguntaTech.model.LoginResult;
import es.metrica.PreguntaTech.model.User;
import es.metrica.PreguntaTech.services.user.UserServices;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:4200/")
public class LoginControllerImpl implements LoginControllerInterface  {

	private final UserServices serv;
	
	
	public LoginControllerImpl(UserServices serv) {
		super();
		this.serv = serv;
	}


    @PostMapping
	@Override
	public LoginResult login(@RequestBody User user) {
		return serv.login(user);
		
	}

	
}
