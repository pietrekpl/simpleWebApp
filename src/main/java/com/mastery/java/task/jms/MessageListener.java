package com.mastery.java.task.jms;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;


import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class MessageListener extends Thread {

   // @JmsListener(destination = "${spring.activemq.queue}", containerFactory = "empJmsContFactory")
    public void getEmployeeListener(Message message) {
        try {
            sleep(3000);
            log.info("Printing message from Listener.. {}", message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}




