package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeDaoTest {


    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeDaoTest(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Test
    void findAll() {
        List<Employee> list = employeeDao.findAll();
        assertThat(list).size().isGreaterThan(0);
    }
}
