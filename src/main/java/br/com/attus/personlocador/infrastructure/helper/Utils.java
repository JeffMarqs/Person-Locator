package br.com.attus.personlocador.infrastructure.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.com.attus.personlocador.domain.entity.Person;
import br.com.attus.personlocador.domain.entity.enums.AddressType;
import br.com.attus.personlocador.infrastructure.exception.DuplicateMainAddressException;
import br.com.attus.personlocador.infrastructure.exception.InvalidAddressTypeException;
import br.com.attus.personlocador.infrastructure.exception.InvalidLocalDateFormatException;
import br.com.attus.personlocador.infrastructure.exception.LocalDateNullException;

public class Utils {
	
	public static boolean isStringEmptyOrNull(String str) {
	    return str == null || str.isEmpty();
	}

	
	public static String convertNameToFullName(String firstName, String lastName) {
		if (isStringEmptyOrNull(lastName) && isStringEmptyOrNull(firstName))
			throw new IllegalArgumentException("First name and last name cannot be null or empty");
		
		return firstName.trim() + " " + lastName.trim();
	}
	
	public static String[] convertFullNameToName(String fullName) {
		if(isStringEmptyOrNull(fullName))
			throw new IllegalArgumentException("Full name cannot be null or empty");
		
		var name = fullName.split(" ");
		if(name.length < 2)
			throw new IllegalArgumentException("To complete your registration, please enter your surname.");
		
		return name;
	}
	
	public static String localDateToString(LocalDate localDate) {
	    if (localDate == null)
	        throw new LocalDateNullException();

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return localDate.format(formatter);
	}
	
	public static LocalDate stringToLocalDate(String dateString) {
		try {
			return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException e) {
			throw new InvalidLocalDateFormatException(dateString);
		}
	}
	
	public static AddressType validateAddressType(String type) {
		if(!type.equalsIgnoreCase(Constants.MAIN) && !type.equalsIgnoreCase(Constants.SECODARY))
			throw new InvalidAddressTypeException(type);
			
		return AddressType.valueOf(type.toUpperCase());
		
	}
	
	public static void hasMainAddressConflict(Person person, String type) {
		if(type.equalsIgnoreCase(Constants.MAIN)) {
			 boolean hasMainAddress = person.getAddresses().stream()
		                .anyMatch(address -> address.getType() == AddressType.MAIN);
			 
			 if(hasMainAddress)
				 throw new DuplicateMainAddressException();
		}
		
	}

}
