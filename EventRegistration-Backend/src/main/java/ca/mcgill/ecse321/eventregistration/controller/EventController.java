package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.EventDto;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.service.EventService;

@RestController
public class EventController {

	@Autowired
	EventService eventService;
	
	@GetMapping("/event/{name}")
	public ResponseEntity<EventDto> getEventByName(@PathVariable String name) {
		Event event = eventService.getEventByName(name);
		return new ResponseEntity<EventDto>(new EventDto(event), HttpStatus.OK);
	}
	
	@PostMapping("/event")
	public ResponseEntity<EventDto> createEvent(@RequestBody Event event) {
		event = eventService.createEvent(event);
		return new ResponseEntity<EventDto>(new EventDto(event), HttpStatus.CREATED);
	}
}
