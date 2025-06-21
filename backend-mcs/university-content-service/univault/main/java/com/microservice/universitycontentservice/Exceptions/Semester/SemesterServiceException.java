package com.microservice.universitycontentservice.Exceptions.Semester;

public class SemesterServiceException extends RuntimeException {
    public SemesterServiceException(String message) {
        super(message);
    }
}
