package com.mastery.java.task.service;

import com.mastery.java.task.exception.ResourceNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;



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


    public void deleteEmployeeById(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        }

    }

    public Employee updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        existingEmployee.setGender(employee.getGender());
        return employeeRepository.save(existingEmployee);

    }


    public List<Employee> filterEmployeesByFirstNameOrLastName(String firstName, String lastName) {
       return employeeRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName);

    }
}
