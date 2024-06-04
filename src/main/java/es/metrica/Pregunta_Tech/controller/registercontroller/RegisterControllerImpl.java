package es.metrica.Pregunta_Tech.controller.registercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import es.metrica.Pregunta_Tech.model.User;
import es.metrica.Pregunta_Tech.services.user.UserServices;
@RestController
@RequestMapping("/register")
@CrossOrigin("http://localhost:4200/")
public class RegisterControllerImpl implements RegisterControllerInterface{

	
	private final	UserServices serv;
	
	@Autowired
	public RegisterControllerImpl(UserServices serv) {
		
		this.serv=serv;
		
	}
	

	@PostMapping
	@Override
	public User register(@RequestBody User user) {
		return serv.register(user);
	}

}
