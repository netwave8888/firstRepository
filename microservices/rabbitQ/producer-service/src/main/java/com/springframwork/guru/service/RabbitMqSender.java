package com.springframwork.guru.service;

import com.springframwork.guru.domain.User;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    private static final String ROUTING_KEY_USER_IMPORTANT_WARN = "*.important.*";

	private static final String ROUTING_KEY_USER_IMPORTANT_ERROR = "#.error";

	private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    TopicExchange topicExchange;

    @Value("${spring.rabbitmq.fanoutexchange}")
    private String fanoutexchange;
    
    @Value("${spring.rabbitmq.topicexchange}")
    private String topicexchange;
//
//    @Value("${spring.rabbitmq.routingkey}")
//    private String routingkey;

    public void send(User user){
        // rabbitTemplate.convertAndSend(topicExchange,routingkey, user);
    	
    	String message = " payload is broadcast";
        
        rabbitTemplate.convertAndSend(fanoutexchange, "", "fanout" + message);
        rabbitTemplate.convertAndSend(topicexchange, ROUTING_KEY_USER_IMPORTANT_WARN, "topic important warn" + message);
        rabbitTemplate.convertAndSend(topicexchange, ROUTING_KEY_USER_IMPORTANT_ERROR, 
                "topic important error" + message);

    }
    
    

}
