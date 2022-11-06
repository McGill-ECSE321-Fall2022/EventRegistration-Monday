package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.eventregistration.dto.RegistrationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.RegistrationResponseDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepo;
	@Autowired
	private EventService eventService;
	@Autowired
	private PersonService personService;
	
	@Transactional
	public RegistrationResponseDto getRegistrationById(int id) {
		Registration registration = registrationRepo.findRegistrationById(id);
		if (registration == null) {
			throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Registration not found.");
		}
		return new RegistrationResponseDto(registration);
	}
	
	@Transactional
	public RegistrationResponseDto createRegistration(RegistrationRequestDto registrationRequest) {
		Person participant = personService.getPersonById(registrationRequest.getParticipantId());
		Event event = eventService.getEventByName(registrationRequest.getEventName());

		Registration registration = new Registration();
		registration.setParticipant(participant);
		registration.setEvent(event);
		
		registration = registrationRepo.save(registration);
		return new RegistrationResponseDto(registration);
	}
}
