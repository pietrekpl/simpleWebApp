package com.mastery.java.task.controller;


import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
       return employeeService.addNewEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            employeeService.updateEmployee(employee,id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@GetMapping("/employees/{firstName}")
    public List<Employee> getEmployeesByFirstName(@PathVariable("firstName") String firstName){
        return employeeService.findByFirstName(firstName);
    }*/

    // to do :  filter Employees by FirstName and LastName
}




