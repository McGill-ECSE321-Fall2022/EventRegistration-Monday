package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.eventregistration.model.Event;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventRepositoryTests {
	@Autowired
	private EventRepository eventRepository;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		eventRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadEvent() {
		String name = "Order 66";
		Date date = Date.valueOf("2022-09-06");
		Time startTime = Time.valueOf("16:00:00");
		Time endTime = Time.valueOf("17:00:00");
		Event event = new Event();
		event.setName(name);
		event.setEventDate(date);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		
		eventRepository.save(event);
		
		event = null;
		
		event = eventRepository.findEventByName(name);
		
		assertNotNull(event);
		assertEquals(name, event.getName());
		assertEquals(date, event.getEventDate());
		assertEquals(startTime, event.getStartTime());
		assertEquals(endTime, event.getEndTime());
	}
}
