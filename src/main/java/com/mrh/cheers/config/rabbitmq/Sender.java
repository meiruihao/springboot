package com.mrh.cheers.config.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String queueName,String content) {
        this.rabbitTemplate.convertAndSend(queueName,content);
    }
}
