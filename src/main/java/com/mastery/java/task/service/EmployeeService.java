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

    // correct method
    public void deleteEmployeeById(Long id) {
        boolean isExistingEmployee = employeeRepository.existsById(id);
        if (isExistingEmployee) {
            employeeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Employee with ID " + id + " not found");
        }
    }

    public Employee updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        return employeeRepository.save(existingEmployee);

    }


    public List<Employee> filterEmployeesByFirstNameOrLastName(String firstName, String lastName) {
        List<Employee> filteredEmployeesByFirstNameOrLastName = employeeRepository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
        if ((firstName == null && lastName == null) || filteredEmployeesByFirstNameOrLastName.isEmpty()) {
            return employeeRepository.findAll();
        }
        return filteredEmployeesByFirstNameOrLastName;
    }


}
