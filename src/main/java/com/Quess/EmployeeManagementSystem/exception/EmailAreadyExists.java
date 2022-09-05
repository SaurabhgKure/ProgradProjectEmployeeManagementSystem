package com.Quess.EmployeeManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAreadyExists extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAreadyExists(String message) {
        super(message);
    }
}
