package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepo;

	@Transactional
	public Event getEventByName(String name) {
		Event event = eventRepo.findEventByName(name);
		if (event == null) {
			throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Event not found.");
		}
		return event;
	}

	@Transactional
	public Event createEvent(Event event) {
		// This isn't an ideal way of checking for conflicts because it's not
		// thread-safe and it requires an extra DB request. In practice, it might be
		// better to use an integer ID for events, put a unique constraint on the name,
		// and catch the DataIntegrityViolationException from the repository.
		if (eventRepo.findEventByName(event.getName()) != null) {
			throw new EventRegistrationException(HttpStatus.CONFLICT, "An event with the given name already exists.");
		}

		event = eventRepo.save(event);
		return event;
	}
}
