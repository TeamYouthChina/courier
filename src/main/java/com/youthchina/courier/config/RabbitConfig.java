package com.youthchina.courier.config;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan(basePackages = {"com.youthchina.courier"})
@PropertySource(value = {"classpath:application.properties"})
public class RabbitConfig {
    @Bean
    public Queue hello() {
        return new Queue("hello", true);
    }

    @Bean
    @ConditionalOnBean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory) {
        rabbitListenerContainerFactory.setMessageConverter(jsonMessageConverter());
        return rabbitListenerContainerFactory;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        messageConverter.setClassMapper(new ClassMapper() {
            @Override
            public void fromClass(Class<?> aClass, MessageProperties messageProperties) {
                throw new UnsupportedOperationException("this mapper is only for inbound, do not use for send message");
            }

            @Override
            public Class<?> toClass(MessageProperties messageProperties) {
                String originalName = (String) messageProperties.getHeaders().get("__TypeId__");
                String[] names = originalName.split("\\.");
                try {
                    return Class.forName("com.youthchina.courier.dto." + names[names.length - 1]);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        return messageConverter;
    }
}

