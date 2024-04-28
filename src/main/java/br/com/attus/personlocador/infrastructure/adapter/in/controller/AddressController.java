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

import br.com.attus.personlocador.application.port.in.AddressService;
import br.com.attus.personlocador.domain.dto.AddressRequestDTO;
import br.com.attus.personlocador.domain.dto.AddressResponseDTO;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService serviceAddress;
	
	@GetMapping
	public ResponseEntity<List<AddressResponseDTO>> findAllAddress() {
		return ResponseEntity.ok().body(serviceAddress.findAllAddress());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressResponseDTO> findPersonId(@PathVariable Long id) {
		return ResponseEntity.ok().body(serviceAddress.findAddressId(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> addAddress(@RequestBody AddressRequestDTO addressDTO) {
		serviceAddress.addAddress(addressDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateAddress(@PathVariable Long id, @RequestBody AddressRequestDTO addressDTO) {
		serviceAddress.updateAddress(id, addressDTO);
		return ResponseEntity.ok().build();
	}

}
