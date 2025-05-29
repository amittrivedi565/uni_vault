package com.microservice.universitycontentservice.Exceptions.Course;
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
