package es.metrica.Pregunta_Tech.services.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.metrica.Pregunta_Tech.model.User;
import es.metrica.Pregunta_Tech.repository.User.UserRepository;

@Service
public class UserServicesImpl implements UserServices{

	
	private final  UserRepository userRepository;
	
	
	@Autowired
	public UserServicesImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	
	public String login(User userArg) {
		Optional<User>user=userRepository.getByEmail(userArg.getEmail());
		if(user.isPresent() ) {
			if(user.get().getPassword().equals(userArg.getPassword())) return "valid user";
			
			return "invalid password";
		}
		
		
		return "invalid user";
		
	}
	public User register(User user) {
		

		if(!userRepository.getByEmail(user.getEmail()).isPresent()) {
			
			return userRepository.save(user);
		}
		
		return null;
		
	}
	
}
