package com.app.management.system.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>("Cannot delete this sport due to a data integrity violation", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Internal server error: " + ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
