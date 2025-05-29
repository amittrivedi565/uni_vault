package com.microservice.universitycontentservice.Exceptions;

import com.microservice.universitycontentservice.Exceptions.Branch.branchAlreadyExistsException;
import com.microservice.universitycontentservice.Exceptions.Branch.branchNotFoundException;
import com.microservice.universitycontentservice.Exceptions.Branch.branchServiceException;
import com.microservice.universitycontentservice.Exceptions.Course.courseAlreadyExistsException;
import com.microservice.universitycontentservice.Exceptions.Course.courseNotFoundException;
import com.microservice.universitycontentservice.Exceptions.Course.courseServiceException;
import com.microservice.universitycontentservice.Exceptions.Institute.instituteAlreadyExistsException;
import com.microservice.universitycontentservice.Exceptions.Institute.instituteNotFoundException;
import com.microservice.universitycontentservice.Exceptions.Institute.instituteServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class globalErrorHandler {

    // Handles Institute Exceptions
    @ExceptionHandler(instituteServiceException.class)
    public ResponseEntity<?> handleInstituteServiceException(instituteServiceException ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(instituteAlreadyExistsException.class)
    public ResponseEntity<?> handleInstituteAlreadyExistsException(instituteAlreadyExistsException ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT); // 409 Conflict
    }

    @ExceptionHandler(instituteNotFoundException.class)
    public ResponseEntity<?> handleInstituteNotFoundException(instituteNotFoundException ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // 404 Not Found
    }


    // Handle Course Exceptions
    @ExceptionHandler(courseServiceException.class)
    public ResponseEntity<?> handleCourseServiceException(courseServiceException ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(courseAlreadyExistsException.class)
    public ResponseEntity<?> handleCourseAlreadyExistsException(courseAlreadyExistsException ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(courseNotFoundException.class)
    public ResponseEntity<?> handleCourseNotFoundException(courseNotFoundException ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    // Handle Branch Exceptions
    @ExceptionHandler(branchServiceException.class)
    public ResponseEntity<?> handleBranchServiceException(branchServiceException ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(branchAlreadyExistsException.class)
    public ResponseEntity<?> handleBranchAlreadyExistsException(branchAlreadyExistsException ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(branchNotFoundException.class)
    public ResponseEntity<?> handleBranchNotFoundException(branchNotFoundException ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    // Catches all other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        errorDetails error = new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
