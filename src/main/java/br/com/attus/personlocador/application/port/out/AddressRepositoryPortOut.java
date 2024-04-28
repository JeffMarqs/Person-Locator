package br.com.attus.personlocador.application.port.out;

import java.util.List;

import br.com.attus.personlocador.domain.entity.Address;

public interface AddressRepositoryPortOut {

	List<Address> findAllAddress();

	Address findAddressId(Long id);

	void addAddress(Address address);

}
