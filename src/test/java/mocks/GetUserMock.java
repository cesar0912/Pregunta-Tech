package mocks;

import org.springframework.stereotype.Repository;

import es.metrica.Pregunta_Tech.model.User;
@Repository
public class GetUserMock {
	public User getById(Long id) {
		return new User();
	}
} 
