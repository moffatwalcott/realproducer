package com.example.rabbitMQ.springrabbitmqproducer.Configuration;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    Queue queueA(){
        return new Queue("queue.A", false);

    }

    @Bean
    Queue queueB(){
        return new Queue("queue.B", false);

    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct");

    }

    @Bean
    Binding binding(Queue queueA,DirectExchange exchange ){
        return BindingBuilder.bind(queueA)
                .to(exchange)
                .with("routing.A");

    }

    @Bean
    Binding bindingB(Queue queueB,DirectExchange exchange ){
        return BindingBuilder.bind(queueB)
                .to(exchange)
                .with("routing.B");

    }

    @Bean
    MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;

    }
}
