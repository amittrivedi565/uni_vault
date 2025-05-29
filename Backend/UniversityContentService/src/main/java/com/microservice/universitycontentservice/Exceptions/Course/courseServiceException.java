package com.microservice.universitycontentservice.Exceptions.Course;

public class courseServiceException extends RuntimeException {
    public courseServiceException(String message) {
        super(message);
    }
}
