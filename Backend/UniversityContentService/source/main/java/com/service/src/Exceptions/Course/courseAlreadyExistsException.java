package com.service.src.Exceptions.Course;

public class courseAlreadyExistsException extends RuntimeException {
    public courseAlreadyExistsException(String message) {
        super(message);
    }
}
