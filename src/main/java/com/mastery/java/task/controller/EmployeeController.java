package com.mastery.java.task.controller;


import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.getEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/employees")
    public void addEmployee(@RequestBody() Employee employee) {
        employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            employeeService.updateEmployee(employee, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/firstName")
    public ResponseEntity<List<Employee>> getEmployeesByFirstName(@RequestParam("firstName") String firstName) {
        return new ResponseEntity<>(employeeService.getEmployeeByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/employees/lastName")
    public ResponseEntity<List<Employee>> getEmployeesByLastName(@RequestParam("lastName") String lastName) {
        return ResponseEntity.ok(employeeService.searchByLastName(lastName));
    }
}




