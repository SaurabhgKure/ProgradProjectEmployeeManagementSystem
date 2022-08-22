package com.Quess.EmployeeManagementSystem.exception;

import java.util.Date;

public class ErrorDetail {

    private String message;
    private String details;

    public ErrorDetail( String message, String details) {
        super();

        this.message = message;
        this.details = details;
    }

    public ErrorDetail(){

    }


    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
}