package com.mrh.cheers.web.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "test_queue")
public class TestReciver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void process(String params) {
        logger.info("【队列消费者】【test_queue】:get message--->" + params);
    }


}
