package es.metrica.PreguntaTech;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
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
import es.metrica.PreguntaTech.utils.hash.HashingUtil;
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

	@Autowired
	HashingUtil hu;
	@Autowired
	HashingUtil hu2;
	@Autowired
	Jwt jwt;
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

		assertThrows(InvalidQueryException.class, () -> {questionServices.getQuestionsFromApi("https://www.preguntapi.dev/api/categories/javascript?level=aaaa&limit=5");});
		
		assertThrows(InvalidUrlException.class, () -> {questionServices.getQuestionsFromApi("--https://www.preguntapi.dev/api/categories/javascript?level=facil&limit=5");});
		

	}
	@Nested
	class SecurityTests {

		
		@Test
		void contextHashTest(){
			assertNotNull(hu);
			assertNotNull(hu2);
		}
		
		@Test
		void hashingTests() {
			String originalPass = "1234";
			String pass1 = hu.hash(originalPass);
			String pass2 = hu2.hash("54321");
			
			Assertions.assertFalse(hu.verify(pass2, originalPass));
			Assertions.assertFalse(hu2.verify(pass2, originalPass));
			Assertions.assertTrue(hu2.verify(pass1, originalPass));
			Assertions.assertTrue(hu.verify(pass1, originalPass));
		}
		

		
		
		@Test
		void jwtTest() {
			
			User user = new User();
			HashMap<String, Object> hmap = new HashMap<>();
			
			long id = 2345L;
			user.setName("RandomName");
			user.setId(id);
			user.setPassword("R4nd0mP4ssW0rd");
			hmap.put("pass", user.getPassword());
			
			String token = jwt.generateToken(user, hmap);
			
			Assertions.assertEquals(jwt.getUser(token), id);
			
		}
}


}
