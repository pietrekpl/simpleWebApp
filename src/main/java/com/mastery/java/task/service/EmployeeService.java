package com.mastery.java.task.service;


import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

     List<Employee> getAllEmployees();

     Employee getEmployeeById(Long id);

     void addNewEmployee(Employee employee);

     void deleteEmployee(Long id);

     void updateEmployee(Employee employee, Long id);

     List<Employee> findByFirstName(String firstName);

     List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

}
