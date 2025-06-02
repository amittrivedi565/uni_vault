package com.microservice.universitycontentservice.Exceptions;

import com.microservice.universitycontentservice.Exceptions.Branch.BranchServiceException;
import com.microservice.universitycontentservice.Exceptions.Course.CourseServiceException;
import com.microservice.universitycontentservice.Exceptions.Institute.InstituteServiceException;
import com.microservice.universitycontentservice.Exceptions.Semester.SemesterServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalErrorHandler {

    // Handles Institute Exceptions
    @ExceptionHandler(InstituteServiceException.class)
    public ResponseEntity<?> handleInstituteServiceException(InstituteServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CourseServiceException.class)
    public ResponseEntity<String> handleCourseServiceException(CourseServiceException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
    }

    @ExceptionHandler(BranchServiceException.class)
    public ResponseEntity<?> handleBranchServiceException(BranchServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SemesterServiceException.class)
    public ResponseEntity<?> handleSemesterServiceException(SemesterServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
