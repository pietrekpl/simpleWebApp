package com.mastery.java.task.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

// logs ?
@RestControllerAdvice
@Slf4j
public class EmployeeExceptionController {

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleNoEmployeeFoundException(EmployeeNotFoundException exception) {
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(), "Employee with ID " + exception.getId() + " does not exists."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception exception) {
        return buildResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), exception.getMessage()));
    }
}
