package com.mastery.java.task.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    public Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, Long departmentId, String jobTitle) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "employee_id")
    @JsonProperty("employee_id")
    private Long employeeId;


    @Column(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;


    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;


    @Column(name = "department_id")
    @JsonProperty("department_id")
    private Long departmentId;


    @Column(name = "job_title")
    @JsonProperty("job_title")
    private String jobTitle;

    @Transient
    private Gender gender;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
