package com.mastery.java.task.controller;


import com.mastery.java.task.exception.ResourceNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "returns all employees or filter employees based on firstName or lastName param")
    public List<Employee> getFilteredEmployeesOrAllEmployees(@RequestParam(value = "firstName", required = false) String firstName,
                                                             @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.filterEmployeesByFirstNameOrLastName(firstName, lastName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Long id, ResourceNotFoundException exception) {
        log.info("Method getEmployee() takes id = {}", id);
        return employeeService.getEmployeeById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addEmployee(@RequestBody() Employee employee) {
        employeeService.save(employee);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        log.info("Method updateEmployee() takes id = {}", id);
        employeeService.updateEmployee(employee, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        log.info("Method deleteEmployee() takes id = {}", id);
        employeeService.deleteEmployeeById(id);
    }

}




