package mocks;

import org.springframework.stereotype.Repository;

import es.metrica.Pregunta_Tech.model.Users;
@Repository
public class GetUserMock {
	public Users getById(Long id) {
		return new Users();
	}
} 
