package com.mastery.java.task.service;

import com.mastery.java.task.exception.ResourceNotFoundException;
import com.mastery.java.task.jms.JmsEmployeeSender;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.jms.JMSException;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService  {


    private final EmployeeRepository employeeRepository;

    private final JmsEmployeeSender jmsEmployeeSender;

    @Value("${spring.activemq.queue}")
    private String saveQueue;



    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
    }

    public void save(Employee employee)  {
        ActiveMQObjectMessage message = jmsEmployeeSender.sendMessage(employee, saveQueue);
        Employee employeeFromQueue;
        try {
            employeeFromQueue = (Employee) message.getObject();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        employeeRepository.save(employeeFromQueue);

    }

    public void deleteEmployeeById(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        }

    }

    public void updateEmployee(Employee employee, Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setJobTitle(employee.getJobTitle());
            existingEmployee.setDepartmentId(employee.getDepartmentId());
            existingEmployee.setDateOfBirth(employee.getDateOfBirth());
            existingEmployee.setGender(employee.getGender());
          ActiveMQObjectMessage updateMessage = jmsEmployeeSender.sendMessage(existingEmployee, saveQueue);
        try {
            Employee employeeAfterUpdate = (Employee) updateMessage.getObject();
            employeeRepository.save(employeeAfterUpdate);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> filterEmployeesByFirstNameOrLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName);
    }
}
