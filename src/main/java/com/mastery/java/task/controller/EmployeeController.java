package com.mastery.java.task.controller;


import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
@Slf4j
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        log.info("Employees in database : "+ (long) employeeService.getAllEmployees().size());
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody() Employee employee) {
        employeeService.saveEmployee(employee);
        log.info("Employee : " + employee + " has been added to database");
        log.info("Employees in database : "+ (long) employeeService.getAllEmployees().size());
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            employeeService.updateEmployee(employee, id);
            log.info("Employee with ID " + id + " has been successfully updated");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            log.info("Employee not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            employeeService.deleteEmployeeById(id);
            log.info("Employee with id "+ id + " has been deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            log.info("Employee with id "+ id + " has not been found");
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




