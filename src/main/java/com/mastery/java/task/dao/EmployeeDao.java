package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;

public interface EmployeeDao {

    void insertEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeByID(Long employeeID);

    void updateEmployeeById(Employee employee);

    void deleteEmployeeById(Long employeeId);

}
