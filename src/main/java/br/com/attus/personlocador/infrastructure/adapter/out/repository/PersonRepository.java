package br.com.attus.personlocador.infrastructure.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attus.personlocador.domain.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
