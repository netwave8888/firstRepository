package com.springframwork.guru;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

@SpringBootApplication
public class ProducerServiceApplication {

	static final String topicExchangeName = "spring-boot-topicexchange";
	static final String fanoutExchangeName = "spring-boot-fanoutexchange";
	static final String topicQueue1Name = "spring-boot-queue1";
	static final String topicQueue2Name = "spring-boot-queue2";
	
    @Value("${spring.rabbitmq.host}")
    String host;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    String password;

    @Bean
    public Declarables fanoutBindings() {
        Queue fanoutQueue1 = new Queue("fanout.queue1", false);
        Queue fanoutQueue2 = new Queue("fanout.queue2", false);
        FanoutExchange fanoutExchange = new FanoutExchange(fanoutExchangeName);

        return new Declarables(
          fanoutQueue1,
          fanoutQueue2,
          fanoutExchange,
          BindingBuilder.bind(fanoutQueue1).to(fanoutExchange),
          BindingBuilder.bind(fanoutQueue2).to(fanoutExchange));
    }
    
    @Bean
    public Declarables topicBindings() {
        Queue topicQueue1 = new Queue(topicQueue1Name, false);
        Queue topicQueue2 = new Queue(topicQueue2Name, false);

        TopicExchange topicExchange = new TopicExchange(topicExchangeName);

        return new Declarables(
          topicQueue1,
          topicQueue2,
          topicExchange,
          BindingBuilder
            .bind(topicQueue1)
            .to(topicExchange).with("*.important.*"),
          BindingBuilder
            .bind(topicQueue2)
            .to(topicExchange).with("#.error"));
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ProducerServiceApplication.class, args);
    }

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
