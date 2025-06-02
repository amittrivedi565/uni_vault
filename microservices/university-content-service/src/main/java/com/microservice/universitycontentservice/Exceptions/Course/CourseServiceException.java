package com.microservice.universitycontentservice.Exceptions.Course;

public class CourseServiceException extends RuntimeException {
    public CourseServiceException(String message) {
        super(message);
    }
}
