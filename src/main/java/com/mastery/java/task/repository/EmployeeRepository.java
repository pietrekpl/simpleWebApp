package com.mastery.java.task.repository;

import com.mastery.java.task.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameStartsWithOrLastNameStartsWith(String firstName, String lastName);

}
