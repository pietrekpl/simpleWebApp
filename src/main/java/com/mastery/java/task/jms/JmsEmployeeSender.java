package com.mastery.java.task.jms;


import com.mastery.java.task.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
@Slf4j
public class JmsEmployeeSender {

    private final JmsTemplate jmsTemplate;

    public ActiveMQObjectMessage sendMessage(Employee employee, String queueName) {
        ActiveMQObjectMessage objectMessage = new ActiveMQObjectMessage();
        try {
            jmsTemplate.convertAndSend(queueName, employee);
            log.info("Message coming to queue:{}->{}", queueName, employee);
            objectMessage.setObject(employee);
            return objectMessage;
        } catch (Exception exception) {
            log.error("Exception occurred: ", exception);
        }
        return null;
    }

}

