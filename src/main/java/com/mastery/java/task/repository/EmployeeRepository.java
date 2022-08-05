package com.mastery.java.task.repository;

import com.mastery.java.task.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:firstName%")
    List<Employee> findByFirstName(String firstName);


    @Query("SELECT e FROM Employee e WHERE e.lastName LIKE %:lastName%")
    List<Employee> findByLastName(String lastName);

}
