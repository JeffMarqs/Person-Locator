package br.com.attus.personlocador.infrastructure.exception;

public class InvalidAddressTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidAddressTypeException(String type) {
		super("The address type must be MAIN or SECONDARY, but you have provided: " + type);
	}

}
