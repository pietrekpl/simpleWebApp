package com.mastery.java.task.service;

import com.mastery.java.task.exception.ResourceNotFoundException;
import com.mastery.java.task.jms.JmsConsumer;
import com.mastery.java.task.jms.JmsProducer;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final JmsConsumer jmsConsumer;

    private final JmsProducer jmsProducer;


    @Value("${spring.activemq.queue}")
    private String queueName;

    @Value("${spring.activemq.updateQueue}")
    private String updateQueue;



    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
    }

    public void save(Employee employee) {
        jmsProducer.sendMessage(employee, queueName );
        Employee employeeTakenFromQueue = jmsConsumer.receiveMessage(queueName);
        employeeRepository.save(employeeTakenFromQueue);
    }

    public void deleteEmployeeById(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        }

    }

    public Employee updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
        jmsProducer.sendMessage(existingEmployee, updateQueue);
        Employee employeeFromQueue  =  jmsConsumer.receiveMessage(updateQueue);
        if (employeeFromQueue != null) {
            employeeFromQueue.setFirstName(employee.getFirstName());
            employeeFromQueue.setLastName(employee.getLastName());
            employeeFromQueue.setJobTitle(employee.getJobTitle());
            employeeFromQueue.setDepartmentId(employee.getDepartmentId());
            employeeFromQueue.setDateOfBirth(employee.getDateOfBirth());
            employeeFromQueue.setGender(employee.getGender());
        }
            return employeeRepository.save(employeeFromQueue);

    }

    public List<Employee> filterEmployeesByFirstNameOrLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName);
    }
}
