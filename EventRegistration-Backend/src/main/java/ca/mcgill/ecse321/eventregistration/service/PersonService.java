package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.eventregistration.dto.PersonDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepo;
	
	@Transactional
	public PersonDto getPersonById(int id) {
		Person person = personRepo.findPersonById(id);
		if (person == null) {
			throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Person not found.");
		}
		return new PersonDto(person);
	}
	
	@Transactional
	public PersonDto createPerson(Person person) {
		person = personRepo.save(person);
		return new PersonDto(person);
	}
	
}
