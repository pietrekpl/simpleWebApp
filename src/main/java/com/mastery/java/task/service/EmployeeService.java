package com.mastery.java.task.service;


import com.mastery.java.task.dao.EmployeeDao;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
