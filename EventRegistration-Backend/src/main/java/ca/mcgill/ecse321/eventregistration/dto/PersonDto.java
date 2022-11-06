package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonDto {

	private int id;
	
	private String name;
	
	public PersonDto(Person person) {
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
