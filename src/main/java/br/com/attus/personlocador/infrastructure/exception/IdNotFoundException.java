package br.com.attus.personlocador.infrastructure.exception;

public class IdNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IdNotFoundException(Long id, String entityType) {
		super("ID not found for " + entityType + ": " + id);
	}
}
