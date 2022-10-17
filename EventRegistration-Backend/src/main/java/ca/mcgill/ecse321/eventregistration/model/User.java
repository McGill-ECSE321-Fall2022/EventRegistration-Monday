package ca.mcgill.ecse321.eventregistration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
