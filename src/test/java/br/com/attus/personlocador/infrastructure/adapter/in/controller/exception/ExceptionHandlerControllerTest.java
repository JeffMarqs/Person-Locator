package br.com.attus.personlocador.infrastructure.adapter.in.controller.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.attus.personlocador.domain.dto.ErrorDTO;
import br.com.attus.personlocador.infrastructure.exception.DuplicateMainAddressException;
import br.com.attus.personlocador.infrastructure.exception.IdNotFoundException;
import br.com.attus.personlocador.infrastructure.exception.IncompleteNameException;
import br.com.attus.personlocador.infrastructure.exception.InvalidAddressTypeException;
import br.com.attus.personlocador.infrastructure.exception.InvalidLocalDateFormatException;
import br.com.attus.personlocador.infrastructure.exception.LocalDateNullException;

public class ExceptionHandlerControllerTest {

    private final ExceptionHandlerController exceptionHandlerController = new ExceptionHandlerController();

    @Test
    void testHandlerIdNotFoundException() {
    	
    	Long id = 123L;
        String entityType = "Person";
        
        IdNotFoundException ex = new IdNotFoundException(id, entityType);
        ResponseEntity<ErrorDTO> responseEntity = exceptionHandlerController.handlerIdNotFoundException(ex);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Id Not Found", responseEntity.getBody().getError());
        assertEquals("404 NOT_FOUND", responseEntity.getBody().getCode());
        assertEquals("ID not found for Person: 123", responseEntity.getBody().getMessage());
    }
    
    @Test
    void testHandlerLocalDateNullException() {
    	
    	LocalDateNullException ex = new LocalDateNullException();
    	ResponseEntity<ErrorDTO> responseEntity = exceptionHandlerController.handlerLocalDateNullException(ex);
    	assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    	assertEquals("Local Date Null", responseEntity.getBody().getError());
    	assertEquals("400 BAD_REQUEST", responseEntity.getBody().getCode());
    	assertEquals("Date cannot be null.", responseEntity.getBody().getMessage());
    }
    
    @Test
    void testHandlerInvalidAddressTypeException() {
    	
    	String type = "type";
    	
    	InvalidAddressTypeException ex = new InvalidAddressTypeException(type);
    	ResponseEntity<ErrorDTO> responseEntity = exceptionHandlerController.handlerInvalidAddressTypeException(ex);
    	assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    	assertEquals("Invalid Address Type Exception", responseEntity.getBody().getError());
    	assertEquals("400 BAD_REQUEST", responseEntity.getBody().getCode());
    	assertEquals("The address type must be MAIN or SECONDARY, but you have provided: type", responseEntity.getBody().getMessage());
    }
    
    @Test
    void testHandlerInvalidLocalDateFormatException() {
    	
    	String str = "20/04";
    	
    	InvalidLocalDateFormatException ex = new InvalidLocalDateFormatException(str);
    	ResponseEntity<ErrorDTO> responseEntity = exceptionHandlerController.handlerInvalidLocalDateFormatException(ex);
    	assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    	assertEquals("Invalid Local Date FormatException", responseEntity.getBody().getError());
    	assertEquals("400 BAD_REQUEST", responseEntity.getBody().getCode());
    	assertEquals("The string 20/04 is not in valid date format (yyyy-MM-dd).", responseEntity.getBody().getMessage());
    }
    
    @Test
    void testHandlerDuplicateMainAddressException() {
    	
    	DuplicateMainAddressException ex = new DuplicateMainAddressException();
    	ResponseEntity<ErrorDTO> responseEntity = exceptionHandlerController.handlerDuplicateMainAddressException(ex);
    	assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    	assertEquals("Duplicate Main Address Exception", responseEntity.getBody().getError());
    	assertEquals("400 BAD_REQUEST", responseEntity.getBody().getCode());
    	assertEquals("This person already has a main address.", responseEntity.getBody().getMessage());
    }
    
    @Test
    void testHandlerIncompleteNameException() {
    	
    	String msg = "To complete your registration, please enter your surname.";
    	
    	IncompleteNameException ex = new IncompleteNameException(msg);
    	ResponseEntity<ErrorDTO> responseEntity = exceptionHandlerController.handlerIncompleteNameException(ex);
    	assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    	assertEquals("Incomplete Name Exception", responseEntity.getBody().getError());
    	assertEquals("400 BAD_REQUEST", responseEntity.getBody().getCode());
    	assertEquals("To complete your registration, please enter your surname.", responseEntity.getBody().getMessage());
    }
    

}
