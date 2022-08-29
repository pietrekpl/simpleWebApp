package com.mastery.java.task.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import com.mastery.java.task.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class JmsEmployeeConsumer {


    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;

    public Employee receiveMessage(String queueName) {
        try {
            Message message = jmsTemplate.receive(queueName);
            Employee employee = null;
            if (message != null) {
                employee = (Employee) messageConverter.fromMessage(message);
            }
            log.info("Taken message from queue:{}->{}", queueName, employee);
            return employee;

        } catch (JMSException exception) {
            log.error("Error occurred: ", exception);
            throw new RuntimeException(exception);
        }
    }


}






