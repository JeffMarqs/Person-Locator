package br.com.attus.personlocador.domain.entity;

import br.com.attus.personlocador.domain.entity.enums.AddressType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_address")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String street;
	private String zipCode;
	private String number;
	private String city;
	private String state;
	
	@Enumerated(EnumType.STRING)
	private AddressType type;
	
}
