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
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public int addEmployee(Employee employee) {
        String sql = "INSERT into employee(employee_id,first_name,last_name,department_id,job_title) VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql, employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentId(), employee.getJobTitle());
    }

    @Override
    public Optional<Employee> findById(Long id) {
        String sql = "SELECT employee_id, first_name, last_name,department_id, job_title FROM employee where employee_id = ?";
        return jdbcTemplate.query(sql, new EmployeeRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public int deleteEmployee(Long id) {
        String sql = "DELETE FROM employee where employee_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateEmployee(Long id, Employee employee) {
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, job_title = ? WHERE employee_id = ?";
        return jdbcTemplate.update(sql,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                id);
    }
}
