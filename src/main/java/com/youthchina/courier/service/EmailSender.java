package com.youthchina.courier.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send(String m){
        this.rabbitTemplate.convertAndSend("hello", m);
    }
}
