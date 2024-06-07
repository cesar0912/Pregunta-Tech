package es.metrica.PreguntaTech.controller.registercontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.metrica.PreguntaTech.model.User;
import es.metrica.PreguntaTech.services.user.UserServices;

@RestController
@RequestMapping("/register")
@CrossOrigin("http://localhost:4200/")
public class RegisterControllerImpl implements RegisterControllerInterface{

	
	private final	UserServices serv;
	
	public RegisterControllerImpl(UserServices serv) {
		
		this.serv=serv;
		
	}
	

	@PostMapping
	@Override
	public User register(@RequestBody User user) {
		return serv.register(user);
	}

}
