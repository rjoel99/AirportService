package mx.com.ids.practice.controller.exception;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import mx.com.ids.practice.error.ErrorMessage;

/**
 * 
 * @author joel.rubio
 *
 */
@ControllerAdvice
public class ControllerException {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(WebRequest request, EntityNotFoundException exception) {
		
		ErrorMessage message = ErrorMessage.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.message(exception.getMessage())
				.path(request.getContextPath())
				.datetime(LocalDateTime.now())
				.build();
		
		return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
