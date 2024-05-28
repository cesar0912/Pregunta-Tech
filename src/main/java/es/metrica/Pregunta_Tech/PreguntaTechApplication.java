package es.metrica.Pregunta_Tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
//Esta linea evita el error de database hasta que incluyamos nuestra base de datos propia
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) 
public class PreguntaTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreguntaTechApplication.class, args);
	}

}
