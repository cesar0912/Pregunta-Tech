package es.metrica.PreguntaTech.model.exceptions;

public class InvalidQueryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidQueryException(String message) {
		super(message);
	}

}
