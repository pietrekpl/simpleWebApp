package com.mastery.java.task.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import com.mastery.java.task.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class JmsConsumer {


    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;


    // @JmsListener(destination = "testQueue")
    public Employee receiveMessage() {
        try {
            Message message = jmsTemplate.receive();
            Employee employee = (Employee) messageConverter.fromMessage(message);
            log.info("Taken employee:  {} ", employee);
            return employee;

        } catch (JMSException e) {
            log.error("Error occurred: ", e);
            throw new RuntimeException(e);
        }
    }


}






