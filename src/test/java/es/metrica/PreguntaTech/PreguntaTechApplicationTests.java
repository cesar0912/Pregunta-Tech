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
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import es.metrica.PreguntaTech.model.Exam;
import PreguntaTech.utils.model.url.UrlResult;
import es.metrica.PreguntaTech.model.Category;
import es.metrica.PreguntaTech.model.CategoryResponse;
import es.metrica.PreguntaTech.model.LoginResult;
import es.metrica.PreguntaTech.model.Organization;

import es.metrica.PreguntaTech.model.Questions;
import es.metrica.PreguntaTech.model.User;
import es.metrica.PreguntaTech.model.exceptions.InvalidQueryException;
import es.metrica.PreguntaTech.model.exceptions.InvalidUrlException;
import es.metrica.PreguntaTech.repository.exam.ExamRepository;
import es.metrica.PreguntaTech.repository.questions.QuestionsRepository;
import es.metrica.PreguntaTech.repository.user.UserRepository;
import es.metrica.PreguntaTech.services.exam.ExamServices;
import es.metrica.PreguntaTech.services.category.CategoryServices;
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
	private ExamServices examServices;

	@MockBean
	private ExamRepository examRepository;
	@MockBean
	private QuestionsRepository questionRepository;

	@Autowired
	private CategoryServices categoryServices;

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
		assertNotNull(questionServices);
		assertNotNull(examServices);
		assertNotNull(userRepository);
		assertNotNull(questionRepository);
		assertNotNull(examRepository);
		assertNotNull(hu);
		assertNotNull(hu2);
	}

	// login and register tests should be added when logic is being implemented

	@Nested
	class LoginTests {

		@Test
		void testValidUserLogin() {

			String passwordHashed = "$argon2id$v=19$m=1024,t=1,p=1$aiNA9JFgbgmVaB3LV2+EQg$aK76dTL9WU3V9/7RyR/EgkBhjr4Sg+GsNhaXsl19JQY";
			Optional<User> user = Optional.of(new User(1L, "admin", passwordHashed, "", ""));
			when(userRepository.getByEmail("admin")).thenReturn(user);

			User us = new User("admin", "12345", "", "");
			us.setId(1L);
			LoginResult testLogin = userServices.login(us);
			Jwt jwt = new Jwt();

			Assertions.assertEquals(us.getId(), jwt.getUser(testLogin.getToken()));
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

	@Nested
	class QuestionTest {

		@Test
		void classTestQuestions() {
			List<String> answers = new ArrayList<>();
			answers.add("Automatica");
			Questions q = new Questions(1L, "java", "fácil", "Una interfaz se extiende?", answers, null, null);
			Assertions.assertEquals(q.getClass(), Questions.class);
			q.setId(2L);
			Assertions.assertEquals(q.getId(), 2L);
			q.setCategory("python");
			Assertions.assertEquals(q.getCategory(), "python");
			q.setLevel("normal");
			Assertions.assertEquals(q.getLevel(), "normal");
			q.setQuestion("¿Cómo se maneja la memoria en Python?");
			Assertions.assertEquals(q.getQuestion(), "¿Cómo se maneja la memoria en Python?");
			q.setCorrectAnswer("Automatica");
			Assertions.assertEquals(q.getCorrectAnswer(), "Automatica");
			Assertions.assertEquals(q.getAnswers().size(), 1);
			q.setAnswers(List.of());
			Assertions.assertEquals(q.getAnswers().size(), 0);
			q.setFeedBack("Se realiza a través de un montor privado");
			Assertions.assertEquals(q.getFeedBack(), "Se realiza a través de un montor privado");
		}

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

		@Test
		void classCategoryTest() {
			Category category = new Category("Java", 30, "http://localhost:8080/categories");
			Assertions.assertEquals(category.getClass(), Category.class);
			Assertions.assertEquals(category.getName(), "Java");
			Assertions.assertEquals(category.getcount_questions(), 30);
			Assertions.assertEquals(category.getLink(), "http://localhost:8080/categories");
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

	@Nested
	class OrganizationTest {
		@Test
		void organizationTest() {
			List<User> l = new ArrayList<>();
			l.add(new User());
			Organization o = new Organization(1L, "metrica", l);
			Assertions.assertEquals(o.getClass(), Organization.class);
			Assertions.assertEquals(o.getId(), 1L);
			o.setId(2L);
			Assertions.assertEquals(o.getId(), 2L);
			Assertions.assertEquals(o.getName(), "metrica");
			o.setName("metrica consulting");
			Assertions.assertEquals(o.getName(), "metrica consulting");
			Assertions.assertEquals(o.getUsers().size(), 1);
			o.addUsers(new User());
			Assertions.assertEquals(o.getUsers().size(), 2);
			o.setUsers(List.of());
			Assertions.assertEquals(o.getUsers().size(), 0);
		}

	}

	@Nested
	class ExamTest {
		@Test
		void classExamTest() {
			List<Questions> lQuestions = new ArrayList<>();
			List<User> lUser = new ArrayList<>();
			Exam exam = new Exam(1L, lQuestions, lUser);
			Assertions.assertEquals(exam.getClass(), Exam.class);
			Assertions.assertEquals(exam.getUsers().size(), 0);
			exam.setUsers(List.of(new User()));
			Assertions.assertEquals(exam.getUsers().size(), 1);
			Assertions.assertEquals(exam.getQuestions().size(), 0);
			exam.setQuestions(List.of(new Questions()));
			Assertions.assertEquals(exam.getQuestions().size(), 1);
		}
		
		@Test
		void basicTestgetExamsByUser() {
			List<Exam> exam = new ArrayList<>();
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
		
		@Test
		void basicTestgetExams() {
			List<Exam> exam = new ArrayList<>();
			exam.add(new Exam());
			exam.add(new Exam());
		
			when(examRepository.findAll()).thenReturn(exam);
			List<Exam> res = examServices.getExams();
			
			
			assertFalse(res.isEmpty());
		

			}
	}

	@Nested
	class CategoryResponseTest {
		@Test
		void classCategoryResponseTest() {
			List<Category> lCategory = new ArrayList<>();
			CategoryResponse categoryResponse = new CategoryResponse(lCategory, 28, 80);
			Assertions.assertEquals(categoryResponse.getTotalCategories(), 28);
			Assertions.assertEquals(categoryResponse.getTotalQuestions(), 80);

		}
	}

	@Nested
	class UserTest {
		@Test
		void classUserTest() {
			User user = new User("yo@gmail.com", "12345");
			User usercopiar = new User(user.getEmail(), user.getPassword());
			Assertions.assertEquals(user,usercopiar);
			Assertions.assertEquals(user,user);
			Assertions.assertNotEquals(user,null);
			Assertions.assertNotEquals(user,new Exam());
			Assertions.assertEquals(user.getClass(), User.class);
			user.setEmail("yo2@gmail.com");
			user.setSurname("nuevo");
			Assertions.assertEquals(user.toString(),
					"Users [id=" + user.getId() + ", email=" + user.getEmail() + ", password=" + user.getPassword()
							+ ", surname=" + user.getSurname() + ", name=" + user.getName() + "]");
		}

		@Test
		void basicTestSaveExam() {
			Optional<User> user = Optional.of(new User(1L, "user1", "1234", "", ""));
			List<Questions> questions = new ArrayList<>();
			questions.add(new Questions("java", "facil",
					"¿Qué sentencia puede tomar una sola expresión como entrada y luego buscar a través de un número de opciones hasta que se encuentre una que coincida con ese valor?",
					new ArrayList<String>(), "c", "switch"));

			Exam exam = new Exam();
			exam.setQuestions(questions);

			when(userRepository.findById(1L)).thenReturn(user);
			when(questionRepository.saveAll(exam.getQuestions())).thenReturn(questions);
			when(examRepository.save(exam)).thenReturn(new Exam(1L, questions, null));
			when(userRepository.save(user.get())).thenReturn(user.get());
			Map<String, Object> claims = new HashMap<>();
			claims.put("pass", user.get().getPassword());
			assertNotNull(examServices.saveExam(exam, jwt.generateToken(user.get(), claims)));
		}
		@Test
		void basicTestgetExamsByUser() {
			List<Exam> exam = new ArrayList<>();
			exam.add(new Exam());
			exam.add(new Exam());
			User user = new User("", "", "", "");
			user.setId(1L);
			user.setExamUser(exam);
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("pass", "1234");
			String token = jwt.generateToken(user, hm);
			when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
			List<Exam> res = examServices.getExamsUser(token);
			
			assertFalse(res.isEmpty());
			when(userRepository.findById(user.getId())).thenReturn(Optional.of(new User()));
			res = examServices.getExamsUser(token);
			assertNull(res);

			when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(null));
			res=examServices.getExamsUser(token);
			assertTrue(res.isEmpty());
			}
	}

}
