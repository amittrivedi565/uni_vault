package com.univault.ims.exception;

import com.univault.ims.exception.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorHandler.class);

    // Handle Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("message", "Validation failed");
        response.put("fieldErrors", fieldErrors);

        return ResponseEntity.badRequest().body(response);
    }

    // Handle Generic Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Unhandled Exception: {}", ex.getMessage(), ex);
        return buildErrorResponse(ex.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle All Service Exceptions
    @ExceptionHandler({
            InstituteServiceException.class,
            CourseServiceException.class,
            BranchServiceException.class,
            SemesterServiceException.class,
            SubjectServiceException.class,
            UnitServiceException.class
    })
    public ResponseEntity<ErrorDetail> handleServiceExceptions(RuntimeException ex, WebRequest request) {
        logger.warn("ServiceException Caught: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
    }

    // Helper Method to Build ErrorDetail Response
    private ResponseEntity<ErrorDetail> buildErrorResponse(String message, String details, HttpStatus status) {
        ErrorDetail error = new ErrorDetail(new Date(), message, details);
        return new ResponseEntity<>(error, status);
    }
}
