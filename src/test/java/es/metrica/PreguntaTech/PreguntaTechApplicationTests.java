package es.metrica.PreguntaTech;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import PreguntaTech.utils.model.url.UrlResult;
import es.metrica.PreguntaTech.model.Exam;
import es.metrica.PreguntaTech.model.LoginResult;
import es.metrica.PreguntaTech.model.User;
import es.metrica.PreguntaTech.model.exceptions.InvalidQueryException;
import es.metrica.PreguntaTech.model.exceptions.InvalidUrlException;
import es.metrica.PreguntaTech.repository.user.UserRepository;
import es.metrica.PreguntaTech.services.category.CategoryServices;
import es.metrica.PreguntaTech.services.exam.ExamServices;
import es.metrica.PreguntaTech.services.questions.QuestionsServices;
import es.metrica.PreguntaTech.services.user.UserServices;
import es.metrica.PreguntaTech.utils.hash.HashingUtil;
import es.metrica.PreguntaTech.utils.jwt.Jwt;

@ActiveProfiles("test")
@SpringBootTest
class PreguntaTechApplicationTests {

	@Autowired
	private UserServices userServices;

	@Autowired
	private QuestionsServices questionServices;

	@Autowired
	private CategoryServices categoryServices;
	@Autowired
	private ExamServices examServices;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	HashingUtil hu;
	@Autowired
	HashingUtil hu2;
	@Autowired
	Jwt jwt;

	@Test
	void context() {
		assertNotNull(userServices);
		assertNotNull(userRepository);

	}

	// aqui deberiamos de comprobar mas cosas del login y del register cuando
	// tengamos la lógica implementada

	@Nested
	class LoginTests {

		@Test
		void testValidUserLogin() {
			Optional<User> user = Optional.of(new User(1L, "admin", hu.hash("12345"), "", ""));
			when(userRepository.getByEmail("admin")).thenReturn(user);

			LoginResult testLogin = userServices.login(new User("admin", "12345", "", ""));
			Assertions.assertEquals("valid user", testLogin.getToken());
			Assertions.assertNull(testLogin.getError());
		}

		@Test
		void testInvalidUserLogin() {
			Optional<User> user = Optional.of(new User(1L, "admin", hu.hash("12345"), "", ""));
			when(userRepository.getByEmail("admin")).thenReturn(user);

			LoginResult testLogin = userServices.login(new User("nonono", "12345", "", ""));
			Assertions.assertNull(testLogin.getToken());
			Assertions.assertEquals("invalid user", testLogin.getError());
		}

		@Test
		void testInvalidPasswordLogin() {
			Optional<User> user = Optional.of(new User(1L, "admin", hu.hash("12345"), "", ""));
			when(userRepository.getByEmail("admin")).thenReturn(user);

			LoginResult testLogin = userServices.login(new User("admin", "nonono", "", ""));
			Assertions.assertNull(testLogin.getToken());
			Assertions.assertEquals("invalid password", testLogin.getError());
		}

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

	}

	@Nested
	class RegisterTests {
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
		void testValidUserRegister() {
			User user = new User("user1", "user1", "user1", "user1");
			when(userRepository.getByEmail("user1")).thenReturn(Optional.empty());
			when(userRepository.save(user)).thenReturn(user);

			User registeredUser = userServices.register(user);
			Assertions.assertNotNull(registeredUser);
		}

		@Test
		void testInvalidUserRegister() {
			User user = new User("", "", "", "");
			when(userRepository.getByEmail("")).thenReturn(Optional.of(new User()));
			when(userRepository.save(user)).thenReturn(user);

			User registeredUser = userServices.register(user);
			Assertions.assertNull(registeredUser);
		}
	}

	@Test
	void basicTestgetExamsByUser() {
		List<Exam> exam = new ArrayList();
		exam.add(new Exam());
		exam.add(new Exam());
		User user = new User("", "", "", "");
		user.setExamUser(exam);
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		List<Exam> res = examServices.getExamsUser("");
		
		
		assertFalse(res.isEmpty());
		when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
		res = examServices.getExamsUser("");
		assertNull(res);


		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		res=examServices.getExamsUser("");
		assertTrue(res.isEmpty());

	}

	@Nested
	class QuestionTest {
		@Test
		void basicTestQuestions() {
			String url = "https://www.preguntapi.dev/api/categories/javascript?level=facil&limit=5";
			UrlResult urlres = new UrlResult(url);
			List<Object> res = questionServices.getQuestionsFromApi(urlres);
			Assertions.assertFalse(res.isEmpty());
			Assertions.assertEquals(5, res.size());
			urlres.setUrl("https://www.preguntapi.dev/api/categories/javascript?level=aaaa&limit=5");
			assertThrows(InvalidQueryException.class, () -> {
				questionServices.getQuestionsFromApi(urlres);
			});
			urlres.setUrl("--https://www.preguntapi.dev/api/categories/javascript?level=facil&limit=5");
			assertThrows(InvalidUrlException.class, () -> {
				questionServices.getQuestionsFromApi(urlres);
			});

		}
	}

	@Nested
	class CategoryTest {
		@Test
		void basicTestCategory() {

			assertNotNull(categoryServices.getCategories());
			assertFalse(categoryServices.getCategories().getCategories().isEmpty());
		}
	}

	@Nested
	class SecurityTests {

		@Test
		void contextHashTest() {
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
