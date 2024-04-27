package br.com.attus.personlocador.infrastructure.adapter.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attus.personlocador.application.port.in.PersonService;
import br.com.attus.personlocador.domain.dto.PersonRequestDTO;
import br.com.attus.personlocador.domain.dto.PersonResponseDTO;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/locador/{id}")
	public ResponseEntity<PersonResponseDTO> findPersonId(@PathVariable Long id) {
		return ResponseEntity.ok().body(personService.findPersonId(id));
	}
	
	@GetMapping("/locador")
	public ResponseEntity<List<PersonResponseDTO>> findAllPerson() {
		return ResponseEntity.ok().body(personService.findAllPerson());
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> addPerson(@RequestBody PersonRequestDTO person) {
		personService.addPerson(person);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody PersonRequestDTO person) {
		personService.updatePerson(id, person);
		return ResponseEntity.ok().build();
	}

}
