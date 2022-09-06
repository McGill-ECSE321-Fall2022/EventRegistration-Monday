package ca.mcgill.ecse321.eventregistration.model;

public class Person {
	private Integer id;
	private String name;
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	// No need for a setter for id because the database can generate it automatically
	
	public void setName(String name) {
		this.name = name;
	}
}
