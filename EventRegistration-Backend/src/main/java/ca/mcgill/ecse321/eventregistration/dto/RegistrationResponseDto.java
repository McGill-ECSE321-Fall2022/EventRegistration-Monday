package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Registration;

public class RegistrationResponseDto {

	private int id;
	// IMPORTANT: Use DTOs here, not the model classes.
	private PersonDto participant;
	private EventDto event;

	public RegistrationResponseDto(Registration registration) {
		this.id = registration.getId();
		this.participant = new PersonDto(registration.getParticipant());
		this.event = new EventDto(registration.getEvent());
	}
	
	public int getId() {
		return this.id;
	}
	
	public PersonDto getParticipant() {
		return this.participant;
	}
	
	public EventDto getEvent() {
		return this.event;
	}
}
