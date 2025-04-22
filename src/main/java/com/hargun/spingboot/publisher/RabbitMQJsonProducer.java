package com.hargun.spingboot.publisher;

import com.hargun.spingboot.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.json_key}")
    private String rountingJsonKey;

    private AmqpTemplate amqpTemplate;

    public RabbitMQJsonProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendJsonMessage(User user){
        log.info(String.format("Json message sent -> %s",user.toString()));
        amqpTemplate.convertAndSend(exchange,rountingJsonKey,user);
    }

}
