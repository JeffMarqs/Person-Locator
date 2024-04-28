package br.com.attus.personlocador.infrastructure.adapter.out.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attus.personlocador.application.port.out.AddressRepositoryPortOut;
import br.com.attus.personlocador.domain.entity.Address;
import br.com.attus.personlocador.infrastructure.adapter.out.repository.AddressRepository;
import br.com.attus.personlocador.infrastructure.exception.IdNotFoundException;
import br.com.attus.personlocador.infrastructure.helper.Constants;

@Service
public class AddressPersistence implements AddressRepositoryPortOut {
	
	@Autowired
	AddressRepository repository;

	@Override
	public List<Address> findAllAddress() {
		return repository.findAll();
	}

	@Override
	public Address findAddressId(Long id) {
		return repository.findById(id).orElseThrow( () -> new IdNotFoundException(id, Constants.ADDRESS));
	}

	@Override
	public void addAddress(Address address) {
		repository.save(address);
		
	}

}
