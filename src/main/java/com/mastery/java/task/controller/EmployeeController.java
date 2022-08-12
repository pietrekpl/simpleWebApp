package com.mastery.java.task.controller;


import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }


    @PostMapping
    public void addEmployee(@RequestBody() Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping
    public void updateOrCreateEmployee(@RequestBody Employee employee) {
        employeeService.updateOrCreateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/")
    public List<Employee> getEmployeesByFirstNameOrLastName(@RequestParam(value = "firstName", required = false) String firstName,
                                                            @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.filterEmployeesByFirstNameOrLastName(firstName, lastName);
    }
}




