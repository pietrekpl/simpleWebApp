package com.mastery.java.task.service;


import com.mastery.java.task.model.Employee;

import java.util.List;


public interface EmployeeService {

     List<Employee> getAllEmployees();

     Employee getEmployeeById(Long id);

     void save(Employee employee);

     void deleteEmployee(Long id);

     void updateEmployee(Employee employee, Long id);

     List<Employee> findByFirstName(String firstName);



}
