package ca.mcgill.ecse321.eventregistration.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegistrationRequestDto {

	@Min(1)
	private int participantId;
	@NotNull
	@NotBlank
	private String eventName;
	
	public int getParticipantId() {
		return this.participantId;
	}
	
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}
	
	public String getEventName() {
		return this.eventName;
	}
	
	public void setEventName(String eventId) {
		this.eventName = eventId;
	}
}
