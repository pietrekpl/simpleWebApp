package com.mastery.java.task.jms;


import com.mastery.java.task.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Slf4j
public class JmsConsumer implements MessageListener {


    @JmsListener(destination = "${spring.activemq.queue}")
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Employee employee = (Employee) objectMessage.getObject();
            log.info("Message received: {} ", employee);
        } catch (Exception e) {
            log.error("Error occurred: ", e);
        }
    }
}
