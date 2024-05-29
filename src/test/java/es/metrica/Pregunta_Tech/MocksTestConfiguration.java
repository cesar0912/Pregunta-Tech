package es.metrica.Pregunta_Tech;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import es.metrica.Pregunta_Tech.repository.User.UserRepository;
import es.metrica.Pregunta_Tech.services.user.UserServicesImpl;
import mocks.GetUserMock;

@Profile("test")
@Configuration
public class MocksTestConfiguration {

//En esta clase se crean las instancias mockeadas de las clases que sean necesarias
//En principio seria para los repositorios porque son interfaces aunque tengo que investigar mas para entenderlo mejor
//los servicios no necesitan ser mockeados solamente injectados
	@Bean
	@Primary
	public UserRepository nameService() {
		return Mockito.mock(UserRepository.class);
	}
}
