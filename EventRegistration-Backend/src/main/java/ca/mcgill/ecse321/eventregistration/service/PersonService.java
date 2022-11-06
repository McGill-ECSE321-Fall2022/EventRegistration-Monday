package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepo;
	
	@Transactional
	public Person getPersonById(int id) {
		Person person = personRepo.findPersonById(id);
		if (person == null) {
			throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Person not found.");
		}
		return person;
	}
	
	@Transactional
	public Person createPerson(Person person) {
		person = personRepo.save(person);
		return person;
	}
}
