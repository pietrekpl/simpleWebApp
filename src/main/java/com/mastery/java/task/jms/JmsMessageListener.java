package com.mastery.java.task.jms;


import com.mastery.java.task.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;


@Component
@Slf4j
@RequiredArgsConstructor
public class JmsMessageListener  {

    @JmsListener(destination = "${spring.activemq.queue}", containerFactory = "empJmsContFactory")
    public void getEmployeeListener(ActiveMQObjectMessage queueMessage) {

        try {
            Employee employee = (Employee) queueMessage.getObject();
            log.info("Message From Listener: {}", employee);
        } catch (JMSException exception) {
            log.error("Exception occurred: ", exception);
            throw new RuntimeException(exception);
        }
    }
}




