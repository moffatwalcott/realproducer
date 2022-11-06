package com.example.rabbitMQ.springrabbitmqproducer.Controller;

import com.example.rabbitMQ.springrabbitmqproducer.Configuration.RabbitMQConfiguration;
import com.example.rabbitMQ.springrabbitmqproducer.model.Message;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @PostMapping("/post")
    private String send(@RequestBody Message message){

        rabbitTemplate.convertAndSend(exchange.getName(),"routing.", message);
        return "Message sent successfully";
    }
}
