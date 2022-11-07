package ca.mcgill.ecse321.eventregistration.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonRequestDto {

	@NotNull
	@NotBlank
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Person toModel() {
		Person person = new Person();
		person.setName(this.name);
		return person;
	}
}
