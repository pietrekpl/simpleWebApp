package com.mastery.java.task.service;

import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        log.info("Method getEmployeeById() takes Id {}", id);
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        boolean existingEmployee = employeeRepository.existsById(id);
        if (existingEmployee) {
            employeeRepository.deleteById(id);
            log.info("Method deleteEmployeeById() takes Id {}", id);
        } else {
            EmployeeNotFoundException exception = new EmployeeNotFoundException(id);
            log.error("Method deleteEmployeeById() takes Id {} ", id);
            log.error("Exception occurred in : ", exception);
            throw exception;
        }
    }

    public void updateOrCreateEmployee(Employee employee) {
        for (Employee existingEmployee : employeeRepository.findAll()) {
            if (Objects.equals(employee.getEmployeeId(), existingEmployee.getEmployeeId())) {
                existingEmployee.setFirstName(employee.getFirstName());
                existingEmployee.setLastName(employee.getLastName());
                existingEmployee.setDepartmentId(employee.getDepartmentId());
                existingEmployee.setJobTitle(employee.getJobTitle());
                employeeRepository.save(existingEmployee);
            } else {
                employeeRepository.save(employee);
            }
        }

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
