package ca.mcgill.ecse321.eventregistration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.MultiplePersonResponseDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;

@RestController
@CrossOrigin(origins = "http://localhost:8087")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/person/{id}")
	public ResponseEntity<PersonResponseDto> getPersonById(@PathVariable int id) {
		Person person = personService.getPersonById(id);
		return new ResponseEntity<PersonResponseDto>(new PersonResponseDto(person), HttpStatus.OK);
	}
	
	@GetMapping("/person")
	public ResponseEntity<MultiplePersonResponseDto> getAllPersons() {
		Iterable<Person> persons = personService.getAllPersons();
		MultiplePersonResponseDto response = new MultiplePersonResponseDto(persons);
		return new ResponseEntity<MultiplePersonResponseDto>(response, HttpStatus.OK);
	}

	@PostMapping("/person")
	public ResponseEntity<PersonResponseDto> createPerson(@Valid @RequestBody PersonRequestDto request) {
		Person personToCreate = request.toModel();
		Person createdPerson = personService.createPerson(personToCreate);
		PersonResponseDto response = new PersonResponseDto(createdPerson);
		return new ResponseEntity<PersonResponseDto>(response, HttpStatus.CREATED);
	}
}
