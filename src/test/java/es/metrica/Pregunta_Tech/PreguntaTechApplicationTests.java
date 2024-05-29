package es.metrica.Pregunta_Tech;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

import es.metrica.Pregunta_Tech.repository.User.UserRepository;
import es.metrica.Pregunta_Tech.repository.User.UserRepositoryImpl;
import es.metrica.Pregunta_Tech.services.UserServices;
import mocks.GetUserMock;

@ActiveProfiles("test")
@SpringBootTest
class PreguntaTechApplicationTests {
	
	//los servicios tienen que ser injectados para poder usar sus metodos
	@Autowired
	private UserServices userServices;
	
	//los repositorios deben ser mockeados para poder comprobar cosas sin meternos con la BBDD y comprobar mejor 
	//los datos  que probamos
	@MockBean
    private UserRepository userRepository;
	
	//se comprueba si los repositorios y servicios se han creado correctamente
	@Test 
	void context(){
		assertNotNull(userServices);
		assertNotNull(userRepository);


	}
	
	//aqui deberiamos de comprobar mas cosas del login y del register cuando tengamos la lógica implementada
	@Test
	void basicTestLogin() {
	
		String testLogin = userServices.login("admin", "admin");
		Assertions.assertEquals("Token", testLogin);
	}
	@Test
	void basicTestRegister() {
	

		Boolean testLogin = userServices.register("admin","admin", "admin","admin");
		Assertions.assertEquals(true, testLogin);
		

	}

}





