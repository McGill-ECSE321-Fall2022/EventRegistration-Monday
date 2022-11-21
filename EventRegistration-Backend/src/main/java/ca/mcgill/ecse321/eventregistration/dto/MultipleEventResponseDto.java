package ca.mcgill.ecse321.eventregistration.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.eventregistration.model.Event;

public class MultipleEventResponseDto {

	private Iterable<EventResponseDto> events;
	
	public MultipleEventResponseDto(Iterable<Event> events) {
		List<EventResponseDto> eventDtos = new ArrayList<EventResponseDto>();
		for (Event e : events) {
			eventDtos.add(new EventResponseDto(e));
		}
		this.events = eventDtos;
	}
	
	public Iterable<EventResponseDto> getEvents() {
		return this.events;
	}
}
