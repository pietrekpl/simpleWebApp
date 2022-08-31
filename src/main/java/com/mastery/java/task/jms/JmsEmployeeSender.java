package com.mastery.java.task.jms;


import com.mastery.java.task.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JmsEmployeeSender {

    private final JmsTemplate jmsTemplate;

    public void sendMessage(Employee employee, String queueName) {
        try {
            jmsTemplate.convertAndSend(queueName, employee);
            log.info("Message coming to queue:{}->{}", queueName, employee);
        } catch (Exception exception) {
            log.error("Exception occurred: ", exception);
        }
    }

}

