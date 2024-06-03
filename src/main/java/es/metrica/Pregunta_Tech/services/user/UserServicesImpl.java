package es.metrica.Pregunta_Tech.services.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.metrica.Pregunta_Tech.model.LoginResult;
import es.metrica.Pregunta_Tech.model.User;
import es.metrica.Pregunta_Tech.repository.User.UserRepository;
import es.metrica.Pregunta_Tech.utils.hash.HashingUtil;

@Service
public class UserServicesImpl implements UserServices {

	private final UserRepository userRepository;
	private final HashingUtil hashUtil;

	@Autowired
	public UserServicesImpl(UserRepository userRepository,HashingUtil hashUtil) {
		super();
		this.userRepository = userRepository;
		this.hashUtil=hashUtil;
	}

	public LoginResult login(User userArg) {
		LoginResult res = new LoginResult();
		Optional<User> user = userRepository.getByEmail(userArg.getEmail());
		if (user.isPresent()) {
			if (hashUtil.verify(user.get().getPassword(), userArg.getPassword())) {
				res.setToken("valid user");

			} else {
				res.setError("invalid password");
			}
		} else {
			res.setError("invalid user");
		}

		return res;

	}

	public User register(User user) {

		if (!userRepository.getByEmail(user.getEmail()).isPresent()) {
			user.setPassword(hashUtil.hash(user.getPassword()));
			return userRepository.save(user);
		}

		return null;

	}

}
