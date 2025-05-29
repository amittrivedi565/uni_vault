package com.microservice.universitycontentservice.Exceptions;

import com.microservice.universitycontentservice.Exceptions.Branch.BranchAlreadyExistsException;
import com.microservice.universitycontentservice.Exceptions.Branch.BranchNotFoundException;
import com.microservice.universitycontentservice.Exceptions.Branch.BranchServiceException;
import com.microservice.universitycontentservice.Exceptions.Course.CourseAlreadyExistsException;
import com.microservice.universitycontentservice.Exceptions.Course.CourseNotFoundException;
import com.microservice.universitycontentservice.Exceptions.Course.CourseServiceException;
import com.microservice.universitycontentservice.Exceptions.Institute.InstituteAlreadyExistsException;
import com.microservice.universitycontentservice.Exceptions.Institute.InstituteNotFoundException;
import com.microservice.universitycontentservice.Exceptions.Institute.InstituteServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalErrorHandler {

    // Handles Institute Exceptions
    @ExceptionHandler(InstituteServiceException.class)
    public ResponseEntity<?> handleInstituteServiceException(InstituteServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InstituteAlreadyExistsException.class)
    public ResponseEntity<?> handleInstituteAlreadyExistsException(InstituteAlreadyExistsException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT); // 409 Conflict
    }

    @ExceptionHandler(InstituteNotFoundException.class)
    public ResponseEntity<?> handleInstituteNotFoundException(InstituteNotFoundException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // 404 Not Found
    }


    // Handle Course Exceptions
    @ExceptionHandler(CourseServiceException.class)
    public ResponseEntity<?> handleCourseServiceException(CourseServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<?> handleCourseAlreadyExistsException(CourseAlreadyExistsException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<?> handleCourseNotFoundException(CourseNotFoundException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    // Handle Branch Exceptions
    @ExceptionHandler(BranchServiceException.class)
    public ResponseEntity<?> handleBranchServiceException(BranchServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BranchAlreadyExistsException.class)
    public ResponseEntity<?> handleBranchAlreadyExistsException(BranchAlreadyExistsException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<?> handleBranchNotFoundException(BranchNotFoundException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    // Catches all other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
