package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public PersonDto getPersonById(@PathVariable int id) {
		return personService.getPersonById(id);
	}

	/*
	 * NOTE: if you specify an ID here then it will be accepted and cause unexpected
	 * behaviour. You may want to define a CreatePersonRequestDto with no id
	 * property to avoid this.
	 */
	@PostMapping("/person")
	public PersonDto createPerson(@RequestBody Person person) {
		return personService.createPerson(person);
	}

}
