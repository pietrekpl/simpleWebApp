package com.mastery.java.task.controller;


import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.model.Employee;
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

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        return employeeDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @PutMapping("/update/{id}")
    public int updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        return employeeDao.updateEmployee(id, employee);
    }

    @GetMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeDao.deleteEmployee(id);
    }
}




