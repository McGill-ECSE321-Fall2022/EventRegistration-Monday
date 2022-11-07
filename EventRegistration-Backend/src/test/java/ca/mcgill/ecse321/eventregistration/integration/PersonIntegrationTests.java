package ca.mcgill.ecse321.eventregistration.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonIntegrationTests {

	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private PersonRepository personRepo;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		personRepo.deleteAll();
	}
	
	@Test
	public void testCreateAndGetPerson() {
		int id = testCreatePerson();
		testGetPerson(id);
	}
	
	private int testCreatePerson() {
		ResponseEntity<PersonDto> response = client.postForEntity("/person", new PersonDto("Obi-Wan Kenobi"), PersonDto.class);
		
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Obi-Wan Kenobi", response.getBody().name, "Response has correct name");
		assertTrue(response.getBody().id > 0, "Response has valid ID");
		
		return response.getBody().id;
	}
	
	private void testGetPerson(int id) {
		ResponseEntity<PersonDto> response = client.getForEntity("/person/" + id, PersonDto.class);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Obi-Wan Kenobi", response.getBody().name, "Response has correct name");
		assertTrue(response.getBody().id == id, "Response has correct ID");
	}
	
	@Test
	public void testCreateInvalidPerson() {
		ResponseEntity<String> response = client.postForEntity("/person", new PersonDto("   "), String.class);
		
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Response has correct status");
	}
	
	@Test
	public void testGetInvalidPerson() {
		ResponseEntity<String> response = client.getForEntity("/person/" + Integer.MAX_VALUE, String.class);
		
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Response has correct status");
		assertEquals("Person not found.", response.getBody(), "Response has correct error message");
	}
}

class PersonDto {
	public int id;
	public String name;
	
	// Need default constructor so that Jackson can instantiate the object
	public PersonDto() {}
	
	public PersonDto(String name) {
		this.name = name;
	}
}
