package com.mastery.java.task.repository;

import com.mastery.java.task.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("FROM employeedb WHERE firstName =?1")
    List<Employee> findByFirstName(String firstName);

    @Query("SELECT a FROM employeedb a WHERE a.firstName = ?1 AND lastName = ?2")
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

}
