package com.youthchina.courier.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhongyangwu on 2/21/19.
 */
@Component
@RabbitListener(queues = "hello")
public class EmailRequestReceiver {

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitHandler
    public void onReceived(String message) {
        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!" + message);
    }

    public CountDownLatch getLatch() {
        return latch;

    }
}
