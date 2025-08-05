package com.univault.pyqpk.Exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
        MethodArgumentNotValidException ex
    ) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex
            .getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
            );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Validation failed");
        response.put("fieldErrors", fieldErrors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(PyqpException.class)
    public ResponseEntity<?> handlePyqpException(
        PyqpException ex,
        WebRequest request
    ) {
        ErrorDetails errorDetails = new ErrorDetails(
            new Date(),
            request.getDescription(false),
            ex.getMessage()
        );
        return new ResponseEntity<>(
            errorDetails,
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(KillInterviewException.class)
    public ResponseEntity<?> handleKillInterviewException(
        KillInterviewException ex,
        WebRequest request
    ) {
        ErrorDetails errorDetails = new ErrorDetails(
            new Date(),
            request.getDescription(false),
            ex.getMessage()
        );
        return new ResponseEntity<>(
            errorDetails,
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
