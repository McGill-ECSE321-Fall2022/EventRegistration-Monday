package ca.mcgill.ecse321.eventregistration.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class EventRegistrationException extends RuntimeException {

	private HttpStatus status;
	
	public EventRegistrationException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
}
