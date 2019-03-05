package com.youthchina.courier.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.youthchina.courier"})
@PropertySource(value = {"classpath:application.properties"})
public class RabbitmqConfig {
    @Bean
    public Queue hello(){
        return new Queue("hello");
    }
}
