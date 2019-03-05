package com.youthchina.courier.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(String m){
        this.amqpTemplate.convertAndSend("hello", m);
    }
}
