package ca.mcgill.ecse321.eventregistration.dto;

import java.util.ArrayList;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class MultiplePersonResponseDto {

	private Iterable<PersonResponseDto> persons;
	
	public MultiplePersonResponseDto(Iterable<Person> persons) {
		ArrayList<PersonResponseDto> personDtos = new ArrayList<PersonResponseDto>();
		for (Person p : persons) {
			personDtos.add(new PersonResponseDto(p));
		}
		this.persons = personDtos;
	}
	
	public Iterable<PersonResponseDto> getPersons() {
		return this.persons;
	}
}
