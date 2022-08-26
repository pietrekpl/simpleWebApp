package com.mastery.java.task.jms;


import com.mastery.java.task.model.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JmsProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${spring.activemq.queue}")
    private String queue;


    public void sendMessage(Employee employee) {
        try {
            jmsTemplate.convertAndSend(queue, employee);
            log.info("Message incoming to queue: {}", employee);
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
        }
    }

}

