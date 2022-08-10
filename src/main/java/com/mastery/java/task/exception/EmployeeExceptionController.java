package com.mastery.java.task.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


// do general exception handling
// remove parameter httpServlet
@RestControllerAdvice
public class EmployeeExceptionController  {

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse){
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

   @ExceptionHandler(EmployeeNotFoundException.class)
   public ResponseEntity<Object> handleNoEmployeeFoundException(EmployeeNotFoundException exception){
       return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(),"Employee with ID "+exception.getId()+" does not exists."));
   }




}
