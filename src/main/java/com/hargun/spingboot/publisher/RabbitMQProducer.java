package com.hargun.spingboot.publisher;

import lombok.extern.slf4j.Slf4j;
 import org.springframework.amqp.rabbit.core.RabbitTemplate;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


@Service
@Slf4j
public class RabbitMQProducer {


    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String rountingKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        log.info(String.format("Message sent -> %s",message));
        try{
            rabbitTemplate.convertAndSend(exchange,rountingKey,message);
        }catch (Exception e){
            log.error("Error while sending message",e);
        }
    }



}
