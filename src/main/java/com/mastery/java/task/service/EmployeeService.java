package com.mastery.java.task.service;

import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow();
    }

    public Employee save(Employee employee){
       return employeeRepository.save(employee);
    }
    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(Employee employee, Long id){
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeeByFirstName(String firstName){
        return employeeRepository.findByFirstName(firstName);
    }

    public List<Employee> searchByLastName(String lastName){
        return employeeRepository.findByLastName(lastName);
    }
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

}
