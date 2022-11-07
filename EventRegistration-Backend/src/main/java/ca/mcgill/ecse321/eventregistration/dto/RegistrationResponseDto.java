package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Registration;

public class RegistrationResponseDto {

	private int id;
	// IMPORTANT: Use DTOs here, not the model classes.
	private PersonResponseDto participant;
	private EventResponseDto event;

	public RegistrationResponseDto(Registration registration) {
		this.id = registration.getId();
		this.participant = new PersonResponseDto(registration.getParticipant());
		this.event = new EventResponseDto(registration.getEvent());
	}
	
	public int getId() {
		return this.id;
	}
	
	public PersonResponseDto getParticipant() {
		return this.participant;
	}
	
	public EventResponseDto getEvent() {
		return this.event;
	}
}
