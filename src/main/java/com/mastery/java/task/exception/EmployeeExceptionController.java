package com.mastery.java.task.exception;


import com.mastery.java.task.model.Employee;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class EmployeeExceptionController extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse){
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

   @ExceptionHandler(EmployeeNotFoundException.class)
   public ResponseEntity<Object> handleNoEmployeeFoundException(HttpServletRequest request, EmployeeNotFoundException exception){
       return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(),"Employee with ID "+exception.getId()+" does not exists."));
   }

}
