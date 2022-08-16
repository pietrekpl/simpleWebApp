package com.mastery.java.task.controller;


import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployeesByFirstNameOrLastNameOrGetAllEmployees(@RequestParam(value = "firstName", required = false) String firstName,
                                                                             @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.filterEmployeesByFirstNameOrLastName(firstName, lastName);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }


    @PostMapping
    public void addEmployee(@RequestBody() Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
    }

}




