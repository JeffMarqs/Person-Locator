package br.com.attus.personlocador.application.port.in;

import java.util.List;

import br.com.attus.personlocador.domain.dto.AddressRequestDTO;
import br.com.attus.personlocador.domain.dto.AddressResponseDTO;

public interface AddressService {

	List<AddressResponseDTO> findAllAddress();

	AddressResponseDTO findAddressId(Long id);

	void addAddress(AddressRequestDTO addressDTO);

	void updateAddress(Long id, AddressRequestDTO addressDTO);

}
