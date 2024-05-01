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

import br.com.attus.personlocador.application.port.out.AddressRepositoryPortOut;
import br.com.attus.personlocador.application.port.out.PersonRepositoryPortOut;
import br.com.attus.personlocador.domain.dto.AddressRequestDTO;
import br.com.attus.personlocador.domain.entity.Address;
import br.com.attus.personlocador.domain.entity.Person;
import br.com.attus.personlocador.domain.entity.enums.AddressType;
import br.com.attus.personlocador.infrastructure.exception.IdNotFoundException;
import br.com.attus.personlocador.infrastructure.exception.InvalidAddressTypeException;

public class AddressServiceImplTest {
	
	@Mock
    private AddressRepositoryPortOut addressRepository;
	
	@Mock
    private PersonRepositoryPortOut personRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindAddressById() {
    	
        var mockAddress = buildMockAddress();

        when(addressRepository.findAddressId(mockAddress.getId())).thenReturn(mockAddress);

        var result = addressService.findAddressId(mockAddress.getId());

        assertEquals(1L, result.id());
        assertEquals("Rua Mock", result.street());
        assertEquals("12345-678", result.zipCode());
        assertEquals("MockCity", result.city());
        assertEquals("123", result.number());
        assertEquals("MockState", result.state());
        assertEquals("MAIN", result.type().toString());
    }
    
    @Test
    void testFindAllAddress() {
    	
    	var mockAddress = buildMockAddressList();
    	
    	when(addressRepository.findAllAddress()).thenReturn(mockAddress);
    	
    	var result = addressService.findAllAddress();
    	
    	assertEquals(1L, result.get(0).id());
    	assertEquals("Rua Mock 1", result.get(0).street());
    	assertEquals("12345-678", result.get(0).zipCode());
    	assertEquals("MockCity", result.get(0).city());
    	assertEquals("123", result.get(0).number());
    	assertEquals("MockState", result.get(0).state());
    	assertEquals("MAIN", result.get(0).type().toString());
    	
    	assertEquals(2L, result.get(1).id());
    	assertEquals("Rua Mock 2", result.get(1).street());
    	assertEquals("98765-432", result.get(1).zipCode());
    	assertEquals("MockCity2", result.get(1).city());
    	assertEquals("456", result.get(1).number());
    	assertEquals("MockState2", result.get(1).state());
    	assertEquals("SECONDARY", result.get(1).type().toString());
    	
    	assertEquals(3L, result.get(2).id());
    	assertEquals("Rua Mock 3", result.get(2).street());
    	assertEquals("12345-123", result.get(2).zipCode());
    	assertEquals("MockCity3", result.get(2).city());
    	assertEquals("789", result.get(2).number());
    	assertEquals("MockState3", result.get(2).state());
    	assertEquals("SECONDARY", result.get(2).type().toString());
    }
    
    @Test
    void testAddAddress() {
        var mockPersonDTO = new AddressRequestDTO(
                1L,
                "Rua Mock",
                "12345-678",
                "123",
                "MockCity",
                "MockState",
                "MAIN"
        );
        
        var mockPerson = buildMockPerson();
        
        when(personRepository.findPersonId(mockPerson.getId())).thenReturn(mockPerson);

        addressService.addAddress(mockPersonDTO);

        verify(addressRepository).addAddress(any(Address.class));
    }
    
    @Test
    void testUpdateAddress() {
    	
    	var mockAddress = buildMockAddress();
    	var mockAddressDTO = new AddressRequestDTO(
                1L,
                "Rua Mock",
                "12345-678",
                "123",
                "MockCity",
                "MockState",
                "MAIN"
        );

        when(addressRepository.findAddressId(mockAddress.getId())).thenReturn(mockAddress);
        
        var mockPerson = buildMockPerson();
        
        when(personRepository.findPersonId(mockPerson.getId())).thenReturn(mockPerson);
        
        addressService.updateAddress(mockAddress.getId(), mockAddressDTO);
        
        verify(addressRepository).findAddressId(any(Long.class));

        verify(addressRepository).addAddress(any(Address.class));
    	
    }
    
    @Test
    void testUpdateAddressErrorId() {
    	
    	var mockAddressDTO = new AddressRequestDTO(
    			null,
    			"Rua Mock",
    			"12345-678",
    			"123",
    			"MockCity",
    			"MockState",
    			"MAIN"
    			);
    	
    	assertThrows(IdNotFoundException.class, () -> {
        	addressService.updateAddress(1L, mockAddressDTO);
        });
    	
    }
    
    @Test
    void testAddAddressErrorAddressType() {
        var mockPersonDTO = new AddressRequestDTO(
                1L,
                "Rua Mock",
                "12345-678",
                "123",
                "MockCity",
                "MockState",
                "Test"
        );
        
        assertThrows(InvalidAddressTypeException.class, () -> {
        	addressService.addAddress(mockPersonDTO);
        });

    }
    
    
	private Address buildMockAddress() {
		return Address.builder()
                .id(1L)
                .street("Rua Mock")
                .zipCode("12345-678")
                .number("123")
                .city("MockCity")
                .state("MockState")
                .type(AddressType.MAIN)
                .person(null)
                .build();
	}
	
	public static List<Address> buildMockAddressList() {
        List<Address> addressList = new ArrayList<>();

        addressList.add(Address.builder()
                .id(1L)
                .street("Rua Mock 1")
                .zipCode("12345-678")
                .number("123")
                .city("MockCity")
                .state("MockState")
                .type(AddressType.MAIN)
                .person(null)
                .build());

        addressList.add(Address.builder()
                .id(2L)
                .street("Rua Mock 2")
                .zipCode("98765-432")
                .number("456")
                .city("MockCity2")
                .state("MockState2")
                .type(AddressType.SECONDARY)
                .person(null)
                .build());
        
        addressList.add(Address.builder()
        		.id(3L)
        		.street("Rua Mock 3")
        		.zipCode("12345-123")
        		.number("789")
        		.city("MockCity3")
        		.state("MockState3")
        		.type(AddressType.SECONDARY)
        		.person(null)
        		.build());

        return addressList;
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

}
