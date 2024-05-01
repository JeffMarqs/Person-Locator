package br.com.attus.personlocador.infrastructure.exception;

public class IncompleteNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IncompleteNameException(String msg) {
		super(msg);
	}

}
