package com.mastery.java.task.service;

import com.mastery.java.task.exception.ResourceNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID "+ id+" not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }
    // correct method
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID "+ id+" not found"));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        employeeRepository.save(existingEmployee);

    }

    //correct Method
    public List<Employee> filterEmployeesByFirstNameOrLastName(String firstName, String lastName) {
        List<Employee> listAllEmployees = employeeRepository.findAll();
        for (Employee employee : listAllEmployees) {
            if (employee.getFirstName().equals(firstName) || employee.getLastName().equals(lastName)) {
                return new ArrayList<>(employeeRepository.findByFirstNameStartsWithOrLastNameStartsWith(firstName, lastName));
            }
        }
        return listAllEmployees;
    }


}
