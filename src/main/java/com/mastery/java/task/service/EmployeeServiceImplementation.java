package com.mastery.java.task.service;

import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void save(Employee employee) {
       employeeRepository.save(employee);
    }


    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void updateEmployee(Employee employee, Long id) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findEmployeeByFirstName(firstName);
    }


}
