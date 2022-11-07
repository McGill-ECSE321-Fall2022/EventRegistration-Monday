package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonResponseDto {

	private int id;
	
	private String name;
	
	public PersonResponseDto(Person person) {
		this.id = person.getId();
		this.name = person.getName();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
}
