package es.metrica.Pregunta_Tech.model.exceptions;

public class InvalidUrlException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidUrlException() {
		super("Url invalida");
	}

}
