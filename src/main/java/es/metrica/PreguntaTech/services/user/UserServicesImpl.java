package es.metrica.PreguntaTech.services.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import es.metrica.PreguntaTech.model.LoginResult;
import es.metrica.PreguntaTech.model.User;
import es.metrica.PreguntaTech.repository.user.UserRepository;
import es.metrica.PreguntaTech.utils.hash.HashingUtil;
import es.metrica.PreguntaTech.utils.jwt.Jwt;

@Service
public class UserServicesImpl implements UserServices {

	private final UserRepository userRepository;
	private final HashingUtil hashUtil;

	public UserServicesImpl(UserRepository userRepository,HashingUtil hashUtil) {
		super();
		this.userRepository = userRepository;
		this.hashUtil=hashUtil;
	}

	public LoginResult login(User userArg) {
		LoginResult res = new LoginResult();
		Jwt jwt = new Jwt();
		Optional<User> user = userRepository.getByEmail(userArg.getEmail());
		if (user.isPresent()) {
			String hashedPass = user.get().getPassword();
			if (hashUtil.verify(hashedPass, userArg.getPassword())) {
				Map <String, Object> claims = new HashMap<>();
				claims.put("pass", hashedPass);
				userArg.setId(user.get().getId());
				res.setToken(jwt.generateToken(userArg, claims));

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
