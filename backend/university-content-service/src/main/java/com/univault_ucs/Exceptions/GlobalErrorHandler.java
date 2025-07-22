package com.univault_ucs.Exceptions;

import com.univault_ucs.Exceptions.Branch.BranchServiceException;
import com.univault_ucs.Exceptions.Course.CourseServiceException;
import com.univault_ucs.Exceptions.Institute.InstituteServiceException;
import com.univault_ucs.Exceptions.Semester.SemesterServiceException;
import com.univault_ucs.Exceptions.Subject.SubjectServiceException;
import com.univault_ucs.Exceptions.Unit.UnitServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
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
    public ResponseEntity<?> handleCourseServiceException(CourseServiceException ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
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
