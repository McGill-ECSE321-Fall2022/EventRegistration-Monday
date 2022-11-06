package ca.mcgill.ecse321.eventregistration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.PersonDto;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/person/{id}")
	public ResponseEntity<PersonDto> getPersonById(@PathVariable int id) {
		Person person = personService.getPersonById(id);
		return new ResponseEntity<PersonDto>(new PersonDto(person), HttpStatus.OK);
	}

	/*
	 * NOTE: if you specify an ID here then it will be accepted and cause unexpected
	 * behaviour. You may want to define a CreatePersonRequestDto with no id
	 * property to avoid this.
	 */
	@PostMapping("/person")
	public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody Person person) {
		person = personService.createPerson(person);
		return new ResponseEntity<PersonDto>(new PersonDto(person), HttpStatus.CREATED);
	}
}
