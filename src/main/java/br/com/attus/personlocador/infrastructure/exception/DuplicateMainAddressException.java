package br.com.attus.personlocador.infrastructure.exception;

public class DuplicateMainAddressException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DuplicateMainAddressException(){
		super("This person already has a main address.");
	}

}
