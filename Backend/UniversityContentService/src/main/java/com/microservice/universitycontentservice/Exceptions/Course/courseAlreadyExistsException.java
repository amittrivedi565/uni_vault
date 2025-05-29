package com.microservice.universitycontentservice.Exceptions.Course;

public class courseAlreadyExistsException extends RuntimeException {
    public courseAlreadyExistsException(String message) {
        super(message);
    }
}
