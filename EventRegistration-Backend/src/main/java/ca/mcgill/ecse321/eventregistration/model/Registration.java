package ca.mcgill.ecse321.eventregistration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne(optional = false)
	private User participant;
	@ManyToOne(optional = false)
	private Event event;
	
	public Integer getId() {
		return this.id;
	}
	
	public User getParticipant() {
		return this.participant;
	}
	
	public Event getEvent() {
		return this.event;
	}
	
	// No need for a setter for id because the database can generate it automatically
	
	public void setParticipant(User person) {
		this.participant = person;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
}
