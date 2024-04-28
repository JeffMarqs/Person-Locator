package br.com.attus.personlocador.application.port.service;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attus.personlocador.application.port.in.AddressService;
import br.com.attus.personlocador.application.port.out.AddressRepositoryPortOut;
import br.com.attus.personlocador.application.port.out.PersonRepositoryPortOut;
import br.com.attus.personlocador.domain.dto.AddressRequestDTO;
import br.com.attus.personlocador.domain.dto.AddressResponseDTO;
import br.com.attus.personlocador.domain.entity.Address;
import br.com.attus.personlocador.infrastructure.exception.IdNotFoundException;
import br.com.attus.personlocador.infrastructure.helper.Constants;
import br.com.attus.personlocador.infrastructure.helper.Utils;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepositoryPortOut addressRepository;
	
	@Autowired
	PersonRepositoryPortOut personRepostirory;

	@Override
	public List<AddressResponseDTO> findAllAddress() {
		var addressListDTO = new ArrayList<AddressResponseDTO>();
		var addressList = addressRepository.findAllAddress();

		buildListAddressDTO(addressListDTO, addressList);
		return addressListDTO;
	}

	@Override
	public AddressResponseDTO findAddressId(Long id) {
		var address = addressRepository.findAddressId(id);
		return buildAddressDTO(address);
	}

	@Override
	public void addAddress(AddressRequestDTO addressDTO) {
		var address = new Address();
		buildAddress(addressDTO, address);
		
		addressRepository.addAddress(address);
	}

	@Override
	public void updateAddress(Long id, AddressRequestDTO addressDTO) {
		var address = addressRepository.findAddressId(id);
	    buildAddress(addressDTO, address);
	    
	    addressRepository.addAddress(address);
	}

	private void buildListAddressDTO(List<AddressResponseDTO> addressListDTO, List<Address> addressList) {
		addressList.stream().forEach(address -> {
			var addressDTO = buildAddressDTO(address);

			addressListDTO.add(addressDTO);
		});

	}

	private AddressResponseDTO buildAddressDTO(Address address) {
		return new AddressResponseDTO(
				address.getId(),
				(nonNull(address.getPerson()) ? address.getPerson().getId() : null),
				address.getStreet(),
				address.getZipCode(),
				address.getNumber(),
				address.getCity(),
				address.getState(),
				address.getType().toString());
	}
	
	private void buildAddress(AddressRequestDTO addressDTO, Address address) {
		if(!nonNull(addressDTO.personId()))
			throw new IdNotFoundException(addressDTO.personId(), Constants.PERSON);
		
	    updatePersonIfDifferent(addressDTO, address);

	    Utils.hasMainAddressConflict(address.getPerson(), addressDTO.type());
	    
	    address.setStreet(addressDTO.street());
	    address.setZipCode(addressDTO.zipCode());
	    address.setNumber(addressDTO.number());
	    address.setCity(addressDTO.city());
	    address.setState(addressDTO.state());
	    address.setType(Utils.validateAddressType(addressDTO.type()));
		
	}

	private void updatePersonIfDifferent(AddressRequestDTO addressDTO, Address address) {
		var existingPerson = address.getPerson();

	    if (existingPerson == null || !existingPerson.getId().equals(addressDTO.personId())) {
	        var newPerson = personRepostirory.findPersonId(addressDTO.personId());
	        address.setPerson(newPerson);
	    }
	}
}
