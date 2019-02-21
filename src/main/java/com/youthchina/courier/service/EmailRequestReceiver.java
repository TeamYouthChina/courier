package com.youthchina.courier.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhongyangwu on 2/21/19.
 */
@Component
@RabbitListener(queues = "hello")
public class EmailRequestReceiver {

    @RabbitHandler
    public void onReceived(String message) {

    }
}
