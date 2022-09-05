package com.Quess.EmployeeManagementSystem.exception;



import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
		ErrorDetails errorDetails =
				new ErrorDetails( exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmailAreadyExists.class)
	public ResponseEntity<?>EmailAlreadyExistsHandling (EmailAreadyExists exception, WebRequest request){
		ErrorDetails errorDetails =
				new ErrorDetails( exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> constraintViolationException(ConstraintViolationException constraintViolationException){
		return new ResponseEntity<String>("Enter Correct Organization ID ...!!!",HttpStatus.BAD_REQUEST);
	}

}
