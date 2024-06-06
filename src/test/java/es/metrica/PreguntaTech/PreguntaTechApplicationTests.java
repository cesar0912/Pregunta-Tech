package es.metrica.PreguntaTech;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import es.metrica.PreguntaTech.model.LoginResult;
import es.metrica.PreguntaTech.model.User;
import es.metrica.PreguntaTech.model.exceptions.InvalidQueryException;
import es.metrica.PreguntaTech.model.exceptions.InvalidUrlException;
import es.metrica.PreguntaTech.repository.user.UserRepository;
import es.metrica.PreguntaTech.services.questions.QuestionsServices;
import es.metrica.PreguntaTech.services.user.UserServices;
import es.metrica.PreguntaTech.utils.jwt.Jwt;

@ActiveProfiles("test")
@SpringBootTest
class PreguntaTechApplicationTests {

	// los servicios tienen que ser inyectados para poder usar sus metodos
	@Autowired
	private UserServices userServices;

	@Autowired
	private QuestionsServices questionServices;

	// los repositorios deben ser mockeados para poder comprobar cosas sin meternos
	// con la BBDD y comprobar mejor
	// los datos que probamos
	@MockBean
	private UserRepository userRepository;

	// se comprueba si los repositorios y servicios se han creado correctamente
	@Test
	void context() {
		assertNotNull(userServices);
		assertNotNull(userRepository);

	}

	// aqui deberiamos de comprobar mas cosas del login y del register cuando
	// tengamos la lógica implementada
	@Test
	void basicTestLogin() {
		String passwordHashed = "$argon2id$v=19$m=1024,t=1,p=1$aiNA9JFgbgmVaB3LV2+EQg$aK76dTL9WU3V9/7RyR/EgkBhjr4Sg+GsNhaXsl19JQY";
		Optional<User> user = Optional.of(new User(1L, "admin", passwordHashed, "", ""));
		when(userRepository.getByEmail("admin")).thenReturn(user);
		
		User us = new User("admin", "12345", "", "");
		us.setId(1L);
		LoginResult testLogin = userServices.login(us);
		Jwt jwt = new Jwt();
		
		Assertions.assertEquals(us.getId(), jwt.getUser(testLogin.getToken()));
		Assertions.assertNull(testLogin.getError());

		testLogin = userServices.login(new User("nonono", "12345", "", ""));
		Assertions.assertNull(testLogin.getToken());
		Assertions.assertEquals("invalid user", testLogin.getError());

		testLogin = userServices.login(new User("admin", "nonono", "", ""));
		Assertions.assertNull(testLogin.getToken());
		Assertions.assertEquals("invalid password", testLogin.getError());

	}

	@Test
	void basicTestRegister() {
		User user = new User("user1", "user1", "user1", "user1");
		when(userRepository.getByEmail("user1")).thenReturn(Optional.empty());

		when(userRepository.save(user)).thenReturn(user);

		User testLogin = userServices.register(user);
		Assertions.assertNotNull(testLogin);
		user = new User("", "", "", "");
		when(userRepository.getByEmail("")).thenReturn(Optional.of(new User()));
		when(userRepository.save(user)).thenReturn(user);
		testLogin = userServices.register(user);
		Assertions.assertNull(testLogin);

	}

	@Test
	void basicTestQuestions() {
		String url = "https://www.preguntapi.dev/api/categories/javascript?level=facil&limit=5";
		List<Object> res = questionServices.getQuestionsFromApi(url);
		Assertions.assertFalse(res.isEmpty());
		Assertions.assertEquals(5, res.size());

		assertThrows(InvalidQueryException.class, () -> {questionServices.getQuestionsFromApi("https://www.preguntapi.dev/api/categories/javascript?level=aaaa&limit=5");});
		
		assertThrows(InvalidUrlException.class, () -> {questionServices.getQuestionsFromApi("--https://www.preguntapi.dev/api/categories/javascript?level=facil&limit=5");});
		

	}

}
