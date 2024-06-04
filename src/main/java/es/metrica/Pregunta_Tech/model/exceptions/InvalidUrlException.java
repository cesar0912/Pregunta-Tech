package es.metrica.Pregunta_Tech.model.exceptions;

public class InvalidUrlException  extends RuntimeException {

	public InvalidUrlException() {
		super("Url invalida");
	}

}
