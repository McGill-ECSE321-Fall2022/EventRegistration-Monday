package ca.mcgill.ecse321.eventregistration.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EventRegistrationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EventRegistrationException.class)
	public ResponseEntity<String> handleEventRegistrationException(EventRegistrationException ex) {
		return new ResponseEntity<String>(ex.getMessage(), ex.getStatus());
	}
	
}
