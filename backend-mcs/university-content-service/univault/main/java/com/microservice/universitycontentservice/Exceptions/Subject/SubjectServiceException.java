package com.microservice.universitycontentservice.Exceptions.Subject;

public class SubjectServiceException extends RuntimeException {
    public SubjectServiceException(String message) {
        super(message);
    }
}
