package com.mastery.java.task.service;

import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// add exception handling from employee controller
@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(existingEmployee.getEmployeeId());
    }

    public Employee updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        existingEmployee.setJobTitle(employee.getJobTitle());
        return employeeRepository.save(existingEmployee);
    }

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
