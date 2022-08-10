package com.mastery.java.task.controller;

import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// correct methods  delete / put
// move exception handling to service
// change 2 methods from searching firstName / lastName and merge into one
// add some custom logs
@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        log.info("Employees in database : " + (long) employeeService.getAllEmployees().size());
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("")
    public void addEmployee(@RequestBody() Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            employeeService.updateEmployee(employee, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/firstName")
    public ResponseEntity<List<Employee>> getEmployeesByFirstName(@RequestParam("firstName") String firstName) {
        return new ResponseEntity<>(employeeService.getEmployeeByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/lastName")
    public ResponseEntity<List<Employee>> getEmployeesByLastName(@RequestParam("lastName") String lastName) {
        return ResponseEntity.ok(employeeService.searchByLastName(lastName));
    }
}




