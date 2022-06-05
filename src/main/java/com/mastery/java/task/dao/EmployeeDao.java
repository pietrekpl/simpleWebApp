package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    List<Employee> findAll();

    int addEmployee(Employee employee);

    Optional<Employee> findById(Long id);

    int deleteEmployee(Long id);

    int updateEmployee(Long id, Employee employee);

}
