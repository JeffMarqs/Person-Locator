package br.com.attus.personlocador.domain.dto;

public record AddressRequestDTO(
		Long personId,
		String street,
		String zipCode,
		String number,
		String city,
		String state,
		String type) {

}
