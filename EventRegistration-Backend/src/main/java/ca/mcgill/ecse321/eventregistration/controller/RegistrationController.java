package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.RegistrationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.RegistrationResponseDto;
import ca.mcgill.ecse321.eventregistration.service.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;
	
	@GetMapping("/registration/{id}")
	public ResponseEntity<RegistrationResponseDto> getRegistrationById(@PathVariable int id) {
		RegistrationResponseDto registration = registrationService.getRegistrationById(id);
		return new ResponseEntity<RegistrationResponseDto>(registration, HttpStatus.OK);
	}
	
	@PostMapping("/registration")
	public ResponseEntity<RegistrationResponseDto> createRegistration(@RequestBody RegistrationRequestDto registrationRequest) {
		RegistrationResponseDto registration = registrationService.createRegistration(registrationRequest);
		return new ResponseEntity<RegistrationResponseDto>(registration, HttpStatus.CREATED);
	}
}
