package es.metrica.Pregunta_Tech.repository.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.metrica.Pregunta_Tech.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> getByEmail(String email);
	
	
	 
}