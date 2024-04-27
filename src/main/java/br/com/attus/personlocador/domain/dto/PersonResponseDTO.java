package br.com.attus.personlocador.domain.dto;

import java.util.List;

import br.com.attus.personlocador.domain.entity.Address;

public record PersonResponseDTO(
		String fullName,
		String birthDate,
		List<Address> addresses) {

}
