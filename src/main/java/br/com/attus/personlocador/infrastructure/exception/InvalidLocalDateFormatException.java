package br.com.attus.personlocador.infrastructure.exception;

public class InvalidLocalDateFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidLocalDateFormatException(String str) {
		super("The string " + str + " is not in valid date format (yyyy-MM-dd).");
	}

}
