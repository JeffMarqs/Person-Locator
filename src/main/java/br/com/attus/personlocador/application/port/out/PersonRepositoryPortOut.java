package br.com.attus.personlocador.application.port.out;

import java.util.List;

import br.com.attus.personlocador.domain.entity.Person;

public interface PersonRepositoryPortOut {


	Person findPersonId(Long id);

	void addPerson(Person person);

	 List<Person> findAllPerson();

}
