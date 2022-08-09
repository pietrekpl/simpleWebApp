package com.mastery.java.task.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private final Long id;

    public Long getId() {
        return id;
    }

    public EmployeeNotFoundException(Long id) {
        this.id = id;
    }
}
