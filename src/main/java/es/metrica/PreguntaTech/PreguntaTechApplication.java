package es.metrica.PreguntaTech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication() 
@EnableJpaRepositories("es.metrica.PreguntaTech.repository")
public class PreguntaTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreguntaTechApplication.class, args);
	}

}
