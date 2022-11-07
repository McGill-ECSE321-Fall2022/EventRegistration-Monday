package ca.mcgill.ecse321.eventregistration.dto;

import java.sql.Date;
import java.sql.Time;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ca.mcgill.ecse321.eventregistration.model.Event;

public class EventRequestDto {

	@NotNull
	@NotBlank
	private String name;
	@NotNull
	private Date eventDate;
	@NotNull
	private Time startTime;
	@NotNull
	private Time endTime;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	public Date getEventDate() {
		return this.eventDate;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	public Time getStartTime() {
		return this.startTime;
	}
	
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	public Time getEndTime() {
		return this.endTime;
	}
	
	public Event toModel() {
		Event event = new Event();
		event.setName(this.name);
		event.setEventDate(this.eventDate);
		event.setStartTime(this.startTime);
		event.setEndTime(this.endTime);
		return event;
	}
}
