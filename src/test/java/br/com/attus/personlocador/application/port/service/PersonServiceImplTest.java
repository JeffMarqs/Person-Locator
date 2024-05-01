package br.com.attus.personlocador.application.port.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.attus.personlocador.application.port.out.PersonRepositoryPortOut;
import br.com.attus.personlocador.domain.dto.PersonRequestDTO;
import br.com.attus.personlocador.domain.dto.PersonResponseDTO;
import br.com.attus.personlocador.domain.entity.Person;
import br.com.attus.personlocador.infrastructure.exception.IncompleteNameException;
import br.com.attus.personlocador.infrastructure.exception.InvalidLocalDateFormatException;
import br.com.attus.personlocador.infrastructure.exception.LocalDateNullException;

class PersonServiceImplTest {

    @Mock
    private PersonRepositoryPortOut personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPersonById() {
    	
        var mockPerson = buildMockPerson();

        when(personRepository.findPersonId(mockPerson.getId())).thenReturn(mockPerson);

        PersonResponseDTO result = personService.findPersonId(mockPerson.getId());

        assertEquals(1L, result.id());
        assertEquals("José Claudio", result.fullName());
        assertEquals("15/05/1990", result.birthDate());
    }

    @Test
    void testAddPerson() {
        PersonRequestDTO personDTO = new PersonRequestDTO("Jane Doe", "20/10/1985");

        personService.addPerson(personDTO);

        verify(personRepository).addPerson(any(Person.class));
    }
    
    @Test
    void testFindAllPerson() {
    	
    	var mockPersonList = buildMockListPerson();
    	
    	when(personRepository.findAllPerson()).thenReturn(mockPersonList);
    	
    	var result = personService.findAllPerson();
    	
    	assertEquals(1L, result.get(0).id());
        assertEquals("José Claudio", result.get(0).fullName());
        assertEquals("15/05/1990", result.get(0).birthDate());
        
        assertEquals(2L, result.get(1).id());
        assertEquals("Jefferson Marques", result.get(1).fullName());
        assertEquals("06/09/1998", result.get(1).birthDate());
        
        assertEquals(3L, result.get(2).id());
        assertEquals("Ester Azevedo", result.get(2).fullName());
        assertEquals("05/05/2002", result.get(2).birthDate());
        
        
    }
    
    @Test
    void testUpdatePerson() {
    	
    	var mockPerson = buildMockPerson();
    	var mockPersonDTO = buildMockPersonDTO();

        when(personRepository.findPersonId(mockPerson.getId())).thenReturn(mockPerson);
        
        personService.updatePerson(mockPerson.getId(), mockPersonDTO);
        
        verify(personRepository).findPersonId(any(Long.class));

        verify(personRepository).addPerson(any(Person.class));
    	
    }
    
    @Test
    void testAddPersonErrorNameIncomplete() {
        PersonRequestDTO personDTO = new PersonRequestDTO("Marcos", "20/10/1985");

        assertThrows(IncompleteNameException.class, () -> {
        	personService.addPerson(personDTO);
        });
    }
    
    @Test
    void testAddPersonErrorNameNull() {
    	PersonRequestDTO personDTO = new PersonRequestDTO(null, "20/10/1985");
    	
    	assertThrows(IncompleteNameException.class, () -> {
    		personService.addPerson(personDTO);
    	});
    }
    
    @Test
    void testFindPersonByIdErrorName() {
    	
        Long personId = 1L;
        Person mockPerson = new Person();
        mockPerson.setId(personId);
        mockPerson.setFirstName("José");
        mockPerson.setLastName(null);
        mockPerson.setBirthDate(LocalDate.of(1990, 5, 15));
        
        when(personRepository.findPersonId(mockPerson.getId())).thenReturn(mockPerson);

        assertThrows(IncompleteNameException.class, () -> {
    		personService.findPersonId(personId);
    	});

    }
    
    @Test
    void testFindPersonByIdErrorBirthDateNull() {
    	
    	Long personId = 1L;
    	Person mockPerson = new Person();
    	mockPerson.setId(personId);
    	mockPerson.setFirstName("José");
    	mockPerson.setLastName("Moura");
    	mockPerson.setBirthDate(null);
    	
    	when(personRepository.findPersonId(mockPerson.getId())).thenReturn(mockPerson);
    	
    	assertThrows(LocalDateNullException.class, () -> {
    		personService.findPersonId(personId);
    	});
    	
    }
    
    @Test
    void testAddPersonErrorInvalidLocalDateFormat() {
        PersonRequestDTO personDTO = new PersonRequestDTO("Marcos Jose", "20/24");

        assertThrows(InvalidLocalDateFormatException.class, () -> {
        	personService.addPerson(personDTO);
        });
    }
    
    
    
    private Person buildMockPerson() {
    	
    	Long personId = 1L;
        Person mockPerson = new Person();
        mockPerson.setId(personId);
        mockPerson.setFirstName("José");
        mockPerson.setLastName("Claudio");
        mockPerson.setBirthDate(LocalDate.of(1990, 5, 15));
        
        return mockPerson;
    }
    
    private PersonRequestDTO buildMockPersonDTO() {
    	
    	var mockPerson = new PersonRequestDTO("José Claudio", "15/05/1990");
    	
    	return mockPerson;
    }
    
    private List<Person> buildMockListPerson() {
    	
    	Long personId1 = 1L;
        Person mockPerson1 = new Person();
        mockPerson1.setId(personId1);
        mockPerson1.setFirstName("José");
        mockPerson1.setLastName("Claudio");
        mockPerson1.setBirthDate(LocalDate.of(1990, 5, 15));
        
        Long personId2 = 2L;
        Person mockPerson2 = new Person();
        mockPerson2.setId(personId2);
        mockPerson2.setFirstName("Jefferson");
        mockPerson2.setLastName("Marques");
        mockPerson2.setBirthDate(LocalDate.of(1998, 9, 6));
        
        Long personId3 = 3L;
        Person mockPerson3 = new Person();
        mockPerson3.setId(personId3);
        mockPerson3.setFirstName("Ester");
        mockPerson3.setLastName("Azevedo");
        mockPerson3.setBirthDate(LocalDate.of(2002, 5, 5));
        
        var mockPersonList = new ArrayList<Person>();
        
        mockPersonList.add(mockPerson1);
        mockPersonList.add(mockPerson2);
        mockPersonList.add(mockPerson3);
        
        return mockPersonList;
    	
    }

}

