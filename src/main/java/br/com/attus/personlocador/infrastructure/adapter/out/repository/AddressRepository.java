package br.com.attus.personlocador.infrastructure.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attus.personlocador.domain.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
