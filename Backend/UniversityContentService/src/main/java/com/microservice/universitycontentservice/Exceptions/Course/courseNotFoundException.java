package com.microservice.universitycontentservice.Exceptions.Course;
public class courseNotFoundException extends RuntimeException {
    public courseNotFoundException(String message) {
        super(message);
    }
}
