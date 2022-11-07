package ca.mcgill.ecse321.eventregistration.dto;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.eventregistration.model.Event;

public class EventResponseDto {

	private String name;
	private Date eventDate;
	private Time startTime;
	private Time endTime;
	
	public EventResponseDto(Event event) {
		this.name = event.getName();
		this.eventDate = event.getEventDate();
		this.startTime = event.getStartTime();
		this.endTime = event.getEndTime();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Date getEventDate() {
		return this.eventDate;
	}
	
	public Time getStartTime() {
		return this.startTime;
	}
	
	public Time getEndTime() {
		return this.endTime;
	}
}
