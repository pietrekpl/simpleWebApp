package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
                rs.getLong("employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getLong("department_id"),
                rs.getString("job_title")
        );
    }
}
