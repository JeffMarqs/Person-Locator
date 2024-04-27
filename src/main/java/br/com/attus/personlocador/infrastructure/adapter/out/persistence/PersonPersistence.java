package br.com.attus.personlocador.infrastructure.adapter.out.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attus.personlocador.application.port.out.PersonRepositoryPortOut;
import br.com.attus.personlocador.domain.entity.Person;
import br.com.attus.personlocador.infrastructure.adapter.out.repository.PersonRepository;
import br.com.attus.personlocador.infrastructure.exception.IdNotFoundException;

@Service
public class PersonPersistence implements PersonRepositoryPortOut {
	
	@Autowired
	private PersonRepository repository;

	@Override
	public Person findPersonId(Long id) {
		return repository.findById(id).orElseThrow( () -> new IdNotFoundException(id));
	}

	@Override
	public void addPerson(Person person) {
		repository.save(person);
	}

	@Override
	public List<Person> findAllPerson() {
		return repository.findAll();
	}

}
