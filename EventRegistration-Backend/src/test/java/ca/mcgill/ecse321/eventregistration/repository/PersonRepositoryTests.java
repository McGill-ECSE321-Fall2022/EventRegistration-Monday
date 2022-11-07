package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Person;

@SpringBootTest
public class PersonRepositoryTests {
	@Autowired
	private PersonRepository personRepository;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		personRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadPerson() {
		String name = "Anakin Skywalker";
		Person person = new Person();
		person.setName(name);
		
		person = personRepository.save(person);
		int id = person.getId();
		
		person = null;
		
		person = personRepository.findPersonById(id);
		
		assertNotNull(person);
		assertEquals(id, person.getId());
		assertEquals(name, person.getName());
	}
}
