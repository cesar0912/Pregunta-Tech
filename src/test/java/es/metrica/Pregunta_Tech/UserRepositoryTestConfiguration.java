package es.metrica.Pregunta_Tech;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import es.metrica.Pregunta_Tech.repository.User.UserRepository;
import es.metrica.Pregunta_Tech.repository.User.UserRepositoryImpl;
import es.metrica.Pregunta_Tech.services.UserServices;
import mocks.GetUserMock;
@Profile("test")
@Configuration
public class UserRepositoryTestConfiguration {
		@Bean
	   @Primary
	    public UserServices nameService() {
	        return Mockito.mock(UserServices.class);
	    }
}
