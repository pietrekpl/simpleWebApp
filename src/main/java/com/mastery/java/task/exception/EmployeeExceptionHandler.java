package com.mastery.java.task.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
@Slf4j
public class EmployeeExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(ResourceNotFoundException exception) {
        log.error(exception.getMessage(),exception);
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(), exception.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception exception) {
        log.error(exception.getMessage(),exception);
        return buildResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), exception.getMessage()));
    }
}
