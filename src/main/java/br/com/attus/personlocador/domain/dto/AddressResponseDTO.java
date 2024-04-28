package br.com.attus.personlocador.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public record AddressResponseDTO(
		Long id,
		@JsonInclude(Include.NON_NULL)
		Long personId,
		String street,
		String zipCode,
		String number,
		String city,
		String state,
		String type) {

}
