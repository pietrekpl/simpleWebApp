package com.mastery.java.task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "employee_id")
    private Long employeeId;

    @Getter
    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "department_id")
    private Long departmentId;

    @Getter
    @Setter
    @Column(name = "job_title")
    private String jobTitle;

    private Gender gender;

    public Employee(Long employeeId, String firstName, String lastName, Long departmentId, String jobTitle) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
    }
}
