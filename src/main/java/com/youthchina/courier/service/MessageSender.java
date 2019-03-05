package com.youthchina.courier.service;

import com.youthchina.courier.service.EmailRequestReceiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhongyangwu on 3/2/19.
 */
@Component
public class MessageSender implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final EmailRequestReceiver receiver;

    public MessageSender(EmailRequestReceiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend("hello", "Hello from RabbitMQ!");
    }

}