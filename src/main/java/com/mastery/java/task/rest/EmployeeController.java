package com.mastery.java.task.rest;


import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @GetMapping("/all")
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}




