package ca.mcgill.ecse321.eventregistration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTests {

	// Replace the repository with a "mock" that exposes the same interface
	@Mock
	PersonRepository personRepo;
	
	// Get a service that uses the mock repository
	@InjectMocks
	PersonService personService;
	
	@Test
	public void testGetPersonById() {
		// Tell the mocked repository how to behave
		final int id = 1;
		final String name = "Obi-Wan Kenobi";
		final Person obiWan = new Person(name);
		when(personRepo.findPersonById(id)).thenAnswer((InvocationOnMock invocation) -> obiWan);
		
		// Test that the service behaves properly
		Person person = personService.getPersonById(id);
		
		assertNotNull(person);
		assertEquals(name, person.getName());
	}
	
	@Test
	public void testGetPersonByInvalidId() {
		final int invalidId = 99;
		when(personRepo.findPersonById(invalidId)).thenAnswer((InvocationOnMock invocation) -> null);
		
		EventRegistrationException ex = assertThrows(EventRegistrationException.class, () -> personService.getPersonById(invalidId));
		
		assertEquals("Person not found.", ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
	}
	
	@Test
	public void testCreatePerson() {
		// Just return the Person with no modification
		when(personRepo.save(any(Person.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0));
		
		final String name = "Anakin Skywalker";
		final Person anakin = new Person(name);
		Person returnedPerson = personService.createPerson(anakin);
		
		assertNotNull(returnedPerson);
		assertEquals(name, returnedPerson.getName());
		// Check that the service actually saved the person
		verify(personRepo, times(1)).save(anakin);
	}
}
