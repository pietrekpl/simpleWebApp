package com.mastery.java.task.service;

import com.mastery.java.task.exception.ResourceNotFoundException;
import com.mastery.java.task.jms.JmsEmployeeConsumer;
import com.mastery.java.task.jms.JmsEmployeeProducer;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final JmsEmployeeConsumer jmsEmployeeConsumer;

    private final JmsEmployeeProducer jmsEmployeeProducer;


    @Value("${spring.activemq.queue}")
    private String saveQueue;

    @Value("${spring.activemq.updateQueue}")
    private String updateQueue;



    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
    }

    public void save(Employee employee) {
        jmsEmployeeProducer.sendMessage(employee, saveQueue);
        Employee employeeTakenFromQueue = jmsEmployeeConsumer.receiveMessage(saveQueue);
        employeeRepository.save(employeeTakenFromQueue);
    }

    public void deleteEmployeeById(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        }

    }

    public void updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
        jmsEmployeeProducer.sendMessage(existingEmployee, updateQueue);
        Employee employeeFromQueue  =  jmsEmployeeConsumer.receiveMessage(updateQueue);
        if (employeeFromQueue != null) {
            employeeFromQueue.setFirstName(employee.getFirstName());
            employeeFromQueue.setLastName(employee.getLastName());
            employeeFromQueue.setJobTitle(employee.getJobTitle());
            employeeFromQueue.setDepartmentId(employee.getDepartmentId());
            employeeFromQueue.setDateOfBirth(employee.getDateOfBirth());
            employeeFromQueue.setGender(employee.getGender());
            employeeRepository.save(employeeFromQueue);
        }
    }

    public List<Employee> filterEmployeesByFirstNameOrLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName);
    }
}
