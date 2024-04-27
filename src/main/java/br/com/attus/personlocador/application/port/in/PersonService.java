package br.com.attus.personlocador.application.port.in;

import java.util.List;

import br.com.attus.personlocador.domain.dto.PersonRequestDTO;
import br.com.attus.personlocador.domain.dto.PersonResponseDTO;

public interface PersonService {

	PersonResponseDTO findPersonId(Long id);

	void addPerson(PersonRequestDTO person);

	List<PersonResponseDTO> findAllPerson();

	void updatePerson(Long id, PersonRequestDTO person);

}
