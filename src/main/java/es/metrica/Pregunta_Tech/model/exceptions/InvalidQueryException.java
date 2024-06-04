package es.metrica.Pregunta_Tech.model.exceptions;

public class InvalidQueryException extends RuntimeException{

	public InvalidQueryException(String message) {
		super(message);
	}

}
