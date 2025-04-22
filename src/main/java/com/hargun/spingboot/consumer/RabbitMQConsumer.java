package com.hargun.spingboot.consumer;

import com.hargun.spingboot.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = "${rabbitmq.queue.json_name}")
    public void consumeJsonMessage(Message message) {
        try {
            User user = (User) rabbitTemplate.getMessageConverter().fromMessage(message);
            log.info("Received JSON message: {}", user);
        } catch (Exception e) {
            log.error("Error processing message", e);
        }
    }
}

