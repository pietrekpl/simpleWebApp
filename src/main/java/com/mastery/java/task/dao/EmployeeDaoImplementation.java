package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImplementation implements EmployeeDao {

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    public EmployeeDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insertEmployee(Employee employee) {

    }

    @Override
    public List<Employee> getAllEmployees() {
      return null;
    }

    @Override
    public Employee getEmployeeByID(Long employeeID) {
        return null;
    }

    @Override
    public void updateEmployeeById(Employee employee) {

    }

    @Override
    public void deleteEmployeeById(Long employeeId) {

    }
}
