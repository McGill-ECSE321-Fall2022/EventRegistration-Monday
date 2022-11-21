package ca.mcgill.ecse321.eventregistration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.EventRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.EventResponseDto;
import ca.mcgill.ecse321.eventregistration.dto.MultipleEventResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.service.EventService;

@RestController
@CrossOrigin(origins = "http://localhost:8087")
public class EventController {

	@Autowired
	EventService eventService;
	
	@GetMapping("/event/{name}")
	public ResponseEntity<EventResponseDto> getEventByName(@PathVariable String name) {
		Event event = eventService.getEventByName(name);
		return new ResponseEntity<EventResponseDto>(new EventResponseDto(event), HttpStatus.OK);
	}
	
	@GetMapping("/event")
	public ResponseEntity<MultipleEventResponseDto> getAllEvents() {
		Iterable<Event> events = eventService.getAllEvents();
		MultipleEventResponseDto response = new MultipleEventResponseDto(events);
		return new ResponseEntity<MultipleEventResponseDto>(response, HttpStatus.OK);
	}
	
	@PostMapping("/event")
	public ResponseEntity<EventResponseDto> createEvent(@Valid @RequestBody EventRequestDto request) {
		Event eventToCreate = request.toModel();
		Event createdEvent = eventService.createEvent(eventToCreate);
		EventResponseDto response = new EventResponseDto(createdEvent);
		return new ResponseEntity<EventResponseDto>(response, HttpStatus.CREATED);
	}
}
