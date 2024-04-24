package br.com.attus.personlocador.domain.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private List<Address> address;

}
