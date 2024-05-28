package es.metrica.Pregunta_Tech;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import es.metrica.Pregunta_Tech.repository.User.UserRepository;
import es.metrica.Pregunta_Tech.repository.User.UserRepositoryImpl;
import mocks.GetUserMock;
@Profile("test")
@Configuration
public class UserRepositoryTestConfiguration {
		@Bean
	   @Primary
	    public UserRepositoryImpl nameService() {
	        return Mockito.mock(UserRepositoryImpl.class);
	    }
}
