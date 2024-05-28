package es.metrica.Pregunta_Tech;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import es.metrica.Pregunta_Tech.model.Users;
import es.metrica.Pregunta_Tech.repository.User.UserRepository;
import es.metrica.Pregunta_Tech.repository.User.UserRepositoryImpl;
import mocks.GetUserMock;

@ActiveProfiles("test")
@SpringBootTest
class PreguntaTechApplicationTests {

	@MockBean
	private UserRepositoryImpl userRepositoryImpl;
	

 

	
	@Test
	void contextLoads() {
		
	}
	@Test
	void userRepositoryImpl() {
		
        
	}
	@Test
	void checkgetById() {
		
		when(userRepositoryImpl.getById(1L)).thenReturn(new Users());
		Users testName = userRepositoryImpl.getById( 1L);
		Assertions.assertEquals(new Users(), testName);
	}

}





