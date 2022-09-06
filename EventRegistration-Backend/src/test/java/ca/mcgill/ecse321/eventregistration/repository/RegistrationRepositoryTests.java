package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegistrationRepositoryTests {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@AfterEach
	public void clearDatabase() {
		// Delete the registrations first to avoid violating not-null constraint
		registrationRepository.deleteAll();
		
		personRepository.deleteAll();
		eventRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadRegistration() {
		String eventName = "Order 66";
		Date date = Date.valueOf("2022-09-06");
		Time startTime = Time.valueOf("16:00:00");
		Time endTime = Time.valueOf("17:00:00");
		Event event = new Event();
		event.setName(eventName);
		event.setEventDate(date);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		// You need to manually save this before saving the registration: Hibernate doesn't seem to do it for you
		eventRepository.save(event);
		
		String participantName = "Anakin Skywalker";
		Person participant = new Person();
		participant.setName(participantName);
		participant = personRepository.save(participant);
		int participantId = participant.getId();
		
		Registration registration = new Registration();
		registration.setEvent(event);
		registration.setParticipant(participant);
		
		registration = registrationRepository.save(registration);
		int registrationId = registration.getId();
		
		registration = null;
		event = null;
		participant = null;
		
		registration = registrationRepository.findRegistrationById(registrationId);
		
		assertNotNull(registration);
		assertEquals(registrationId, registration.getId());
		
		assertNotNull(registration.getEvent());
		assertEquals(eventName, registration.getEvent().getName());
		
		assertNotNull(registration.getParticipant());
		assertEquals(participantId, registration.getParticipant().getId());
	}
}
