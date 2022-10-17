package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.eventregistration.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTests {
	@Autowired
	private UserRepository personRepository;
	
	@AfterEach
	public void clearDatabase() {
		personRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadPerson() {
		String name = "Anakin Skywalker";
		User anakin = new User();
		anakin.setName(name);
		
		anakin = personRepository.save(anakin);
		int id = anakin.getId();
		
		anakin = null;
		
		anakin = personRepository.findUserById(id);
		
		assertNotNull(anakin);
		assertEquals(id, anakin.getId());
		assertEquals(name, anakin.getName());
	}
}
