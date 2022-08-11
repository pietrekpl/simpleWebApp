package com.mastery.java.task.service;

import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


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


    public Optional<Employee> getEmployeeById(Long id) {
        boolean isEmployeePresent = employeeRepository.existsById(id);
        if (isEmployeePresent) {
            return employeeRepository.findById(id);
        } else {
            log.error("Error occurred : Method getEmployeeById() takes id = {}", id);
            throw new EmployeeNotFoundException(id);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        boolean isEmployeePresent = employeeRepository.existsById(id);
        if (isEmployeePresent) {
            employeeRepository.deleteById(id);
        } else {
            log.error("Method deleteEmployeeById() takes id : {}", id);
            throw new EmployeeNotFoundException(id);
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
