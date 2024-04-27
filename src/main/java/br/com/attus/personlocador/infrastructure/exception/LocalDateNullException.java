package br.com.attus.personlocador.infrastructure.exception;

public class LocalDateNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LocalDateNullException() {
		super("Date cannot be null.");
	}

}
