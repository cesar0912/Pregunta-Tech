package es.metrica.Pregunta_Tech;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import es.metrica.Pregunta_Tech.model.LoginResult;
import es.metrica.Pregunta_Tech.model.User;
import es.metrica.Pregunta_Tech.model.exceptions.InvalidQueryException;
import es.metrica.Pregunta_Tech.repository.User.UserRepository;
import es.metrica.Pregunta_Tech.services.questions.QuestionsServices;
import es.metrica.Pregunta_Tech.services.user.UserServices;
import es.metrica.Pregunta_Tech.services.user.UserServicesImpl;
import mocks.GetUserMock;

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

		LoginResult testLogin = userServices.login(new User("admin", "12345", "", ""));
		Assertions.assertEquals("valid user", testLogin.getToken());
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
		
	}

}
