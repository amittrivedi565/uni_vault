package com.Exceptions;

import com.Exceptions.Branch.BranchServiceException;
import com.Exceptions.Course.CourseServiceException;
import com.Exceptions.Institute.InstituteServiceException;
import com.Exceptions.Semester.SemesterServiceException;
import com.Exceptions.Subject.SubjectServiceException;
import com.Exceptions.Unit.UnitServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

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

    @ExceptionHandler(SemesterServiceException.class)
    public ResponseEntity<?> handleSemesterServiceException(SemesterServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SubjectServiceException.class)
    public ResponseEntity<?> handleSubjectServiceException(SubjectServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UnitServiceException.class)
    public ResponseEntity<?> handleUnitServiceException(UnitServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
