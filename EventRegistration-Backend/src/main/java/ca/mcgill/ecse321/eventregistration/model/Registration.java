package ca.mcgill.ecse321.eventregistration.model;

public class Registration {
	private Integer id;
	private Person participant;
	private Event event;
	
	public Integer getId() {
		return this.id;
	}
	
	public Person getParticipant() {
		return this.participant;
	}
	
	public Event getEvent() {
		return this.event;
	}
	
	// No need for a setter for id because the database can generate it automatically
	
	public void setParticipant(Person person) {
		this.participant = person;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
}
