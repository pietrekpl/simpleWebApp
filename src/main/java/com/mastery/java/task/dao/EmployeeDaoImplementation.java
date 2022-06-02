package com.mastery.java.task.dao;


import com.mastery.java.task.dto.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImplementation implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public int addEmployee(Employee employee) {
        return 0;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public int deleteEmployee(Long id) {
        return 0;
    }

    @Override
    public int updateEmployee(Long id, Employee employee) {
        return 0;
    }
}
