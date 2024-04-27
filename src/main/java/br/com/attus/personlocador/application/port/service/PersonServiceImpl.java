package br.com.attus.personlocador.application.port.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attus.personlocador.application.port.in.PersonService;
import br.com.attus.personlocador.application.port.out.PersonRepositoryPortOut;
import br.com.attus.personlocador.domain.dto.PersonRequestDTO;
import br.com.attus.personlocador.domain.dto.PersonResponseDTO;
import br.com.attus.personlocador.domain.entity.Person;
import br.com.attus.personlocador.infrastructure.helper.Utils;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepositoryPortOut personRepository;

	@Override
	public PersonResponseDTO findPersonId(Long id) {
		var person = personRepository.findPersonId(id);
		
		return buildPersonDTO(person);
	}

	@Override
	public void addPerson(PersonRequestDTO personDTO) {
		var person = new Person();
		
		buildPerson(person, personDTO);
		
		personRepository.addPerson(person);
	}

	@Override
	public List<PersonResponseDTO> findAllPerson() {
		var personListDTO = new ArrayList<PersonResponseDTO>();
		var personList = personRepository.findAllPerson();
		
		buildListPersonDTO(personListDTO, personList);
		
		return personListDTO;
	}

	@Override
	public void updatePerson(Long id, PersonRequestDTO personDTO) {
		var person = personRepository.findPersonId(id);
		buildPerson(person, personDTO);
		
		personRepository.addPerson(person);
	}
	
	private void buildPerson(Person person, PersonRequestDTO personDTO) {
		person.setFirstName(Utils.convertFullNameToName(personDTO.fullName())[0]);
		person.setLastName(Utils.convertFullNameToName(personDTO.fullName())[1]);
		person.setBirthDate(Utils.stringToLocalDate(personDTO.birthDate()));
	}

	private void buildListPersonDTO(ArrayList<PersonResponseDTO> personListDTO, List<Person> personList) {
		personList.stream().forEach(person -> {
			var personDTO = buildPersonDTO(person);
			
			personListDTO.add(personDTO);
		});
	}
	
	private PersonResponseDTO buildPersonDTO(Person person) {
		return new PersonResponseDTO(Utils.convertNameToFullName(person.getFirstName(), person.getLastName()),
				Utils.localDateToString(person.getBirthDate()), person.getAddresses());
	}
}
