package com.mastery.java.task.repository;

import com.mastery.java.task.model.Employee;
import com.mastery.java.task.model.Gender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(properties = {
        "spring.test.database.replace=NONE"
})
@Slf4j
@DirtiesContext
class EmployeeRepositoryTest {

    // basic schema and insertions are made by liquibase scripts from resources/db/changelog/dbChanges
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldReturnEmptyListWhenNoFirstNameOrLastNameNotFound() {
        //given
        Employee employee = new Employee(null, "xd", "xd", 3L, "Technician", LocalDate.of(2000, 5, 18), Gender.MALE);
        //when
        List<Employee> employeeList = employeeRepository.findByFirstNameContainingAndLastNameContaining(employee.getFirstName(), employee.getLastName());
        //then
        log.info("Employee list size: {}", employeeList.size());
        Assertions.assertEquals(0, employeeList.size());
    }

    @Test
    public void shouldSaveEmployee() {
        //given
        Employee employee = new Employee(null, "Peter", "Paul", 3L, "Technician", LocalDate.of(2000, 5, 18), Gender.MALE);
        Long initialRepositorySize = employeeRepository.count();
        log.info("Employee repository size: {}", initialRepositorySize);
        //when
        employeeRepository.save(employee);
        log.info("Employee repository size: {}", employeeRepository.count());
        //then
        assertThat(employeeRepository.count()).isGreaterThan(initialRepositorySize);
    }

    @Test()
    public void shouldRejectTooYoungEmployee() {
        //given
        Employee employee = new Employee(null, "Peter", "Paul", 3L, "Technician", LocalDate.of(LocalDate.now().getYear() - 17, 1, 1), Gender.MALE);
        log.info("Employee birthDate {}, employee: {}", employee.getDateOfBirth(), employee);
        // when + then
        Assertions.assertThrows(ConstraintViolationException.class, () -> employeeRepository.save(employee));
    }

    @Test
    void shouldDeleteEmployeeFromRepository() {
        //given + when
        Long initialRepositorySize = employeeRepository.count();
        log.info("RepositorySize: {} ", initialRepositorySize);
        employeeRepository.deleteById(1L);
        log.info("RepositorySize: {} ", employeeRepository.count());
        //then
        assertThat(employeeRepository.count()).isLessThan(initialRepositorySize);
    }
    //
}