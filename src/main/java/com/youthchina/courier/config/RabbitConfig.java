package com.youthchina.courier.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.youthchina.courier"})
//@PropertySource(value = {"classpath:application.properties"})
public class RabbitConfig {
    @Bean
    public Queue hello(){
        return new Queue("hello");
    }
}

