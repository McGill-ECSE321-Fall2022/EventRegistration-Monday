package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Event {
	@Id
	@NotNull
	@NotBlank
	private String name;
	private Date eventDate;
	private Time startTime;
	private Time endTime;
	
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEventDate(Date date) {
		this.eventDate = date;
	}
	
	public void setStartTime(Time time) {
		this.startTime = time;
	}
	
	public void setEndTime(Time time) {
		this.endTime = time;
	}
}
