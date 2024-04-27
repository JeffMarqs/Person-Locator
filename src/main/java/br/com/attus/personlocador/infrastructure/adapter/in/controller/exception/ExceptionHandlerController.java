package br.com.attus.personlocador.infrastructure.adapter.in.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.attus.personlocador.domain.dto.ErrorDTO;
import br.com.attus.personlocador.infrastructure.exception.IdNotFoundException;
import br.com.attus.personlocador.infrastructure.exception.InvalidLocalDateFormatException;
import br.com.attus.personlocador.infrastructure.exception.LocalDateNullException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorDTO> handlerIdNotFoundException(IdNotFoundException ex) {
		var error = "Id Not Found";
		var code = HttpStatus.NOT_FOUND.toString();
		var status = HttpStatus.NOT_FOUND.value();
		var menssage = ex.getMessage();
		
		return ResponseEntity.status(status).body(buildErrorDTO(error, code, status, menssage));
	}
	
	@ExceptionHandler(LocalDateNullException.class)
	public ResponseEntity<ErrorDTO> handlerLocalDateNullException(LocalDateNullException ex) {
		var error = "Local Date Null";
		var code = HttpStatus.BAD_REQUEST.toString();
		var status = HttpStatus.BAD_REQUEST.value();
		var menssage = ex.getMessage();
		
		return ResponseEntity.status(status).body(buildErrorDTO(error, code, status, menssage));
	}
	
	@ExceptionHandler(InvalidLocalDateFormatException.class)
	public ResponseEntity<ErrorDTO> handlerInvalidLocalDateFormatException(InvalidLocalDateFormatException ex) {
		var error = "Invalid Local Date Format Exception";
		var code = HttpStatus.BAD_REQUEST.toString();
		var status = HttpStatus.BAD_REQUEST.value();
		var menssage = ex.getMessage();
		
		return ResponseEntity.status(status).body(buildErrorDTO(error, code, status, menssage));
	}

	private ErrorDTO buildErrorDTO(String error, String code, int status, String menssage) {
		return ErrorDTO.builder().code(code)
				.message(menssage)
				.status(status)
				.error(error).build();
	}
}
