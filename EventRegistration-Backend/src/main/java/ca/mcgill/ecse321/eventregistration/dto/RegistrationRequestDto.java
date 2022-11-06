package ca.mcgill.ecse321.eventregistration.dto;

public class RegistrationRequestDto {

	private int participantId;
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
