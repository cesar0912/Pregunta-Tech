package es.metrica.Pregunta_Tech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.metrica.Pregunta_Tech.repository.User.UserRepository;

@Service
public class UserServices {

	
	private final  UserRepository userRepository;
	
	
	@Autowired
	public UserServices(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	
	public String login(String email,String password) {
		
		
		return "Token";
		
	}
	public Boolean register(String email,String password,String name,String surname) {
		
		
		return true;
		
	}
	
}
