package com.mrh.cheers.config.rabbitmq;

import com.mrh.cheers.core.PropertiesFile;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfig {

    private static final String ROUTING_KEY = "_ROUTING_KEY";

    private static final String DELAY = "_DELAY";

    private static final String DIRECT_EXCHANGE = "direct-exchange";

    //延迟队列时间（毫秒） 5分钟
    private static final Long TTL_TIME = 300000L;

    @Autowired
    private PropertiesFile propertiesFile;

    /**
     * 定义交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }


    /**
     * 测试即时队列
     * @return
     */
    @Bean
    public Queue testQueue() {
        Queue queue = new Queue(propertiesFile.testQueue);
        return queue;
    }

    /**
     * 绑定即时队列
     * @param directExchange
     * @param testQueue
     * @return
     */
    @Bean
    public Binding testBinding(DirectExchange directExchange,Queue testQueue) {
        return BindingBuilder.bind(testQueue).to(directExchange).with(propertiesFile.testQueue + ROUTING_KEY);
    }


    /**
     * 测试延迟队列
     * @return
     */
    @Bean
    public Queue testDelayQueue() {
        return QueueBuilder.durable(propertiesFile.testQueue + DELAY)
                .withArgument("x-dead-letter-exchange", DIRECT_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", propertiesFile.testQueue + ROUTING_KEY)
                .withArgument("x-message-ttl", TTL_TIME)
                .build();
    }

    /**
     * 绑定延迟队列
     * @param directExchange
     * @param testDelayQueue
     * @return
     */
    @Bean
    public Binding testDelayBinding(DirectExchange directExchange,Queue testDelayQueue) {
        return BindingBuilder.bind(testDelayQueue).to(directExchange).with(propertiesFile.testQueue + ROUTING_KEY);
    }

}
