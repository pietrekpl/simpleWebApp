package com.mastery.java.task.service;

import com.mastery.java.task.exception.ResourceNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;


    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }


    public Optional<Employee> deleteEmployeeById(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        }
        return Optional.empty();
    }

    public Employee updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        employee.setDateOfBirth(employee.getDateOfBirth());
        employee.setGender(employee.getGender());
        return employeeRepository.save(existingEmployee);

    }


    public List<Employee> filterEmployeesByFirstNameOrLastName(String firstName, String lastName) {
        List<Employee> filteredEmployees = employeeRepository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
        return  (firstName == null && lastName == null) ? employeeRepository.findAll() : filteredEmployees;
    }
}
