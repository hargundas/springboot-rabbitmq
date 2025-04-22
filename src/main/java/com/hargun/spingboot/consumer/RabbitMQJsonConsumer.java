package com.hargun.spingboot.consumer;

import com.hargun.spingboot.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = "${rabbitmq.queue.json_name}")
    public void consumeJsonMessage(User message) {
        log.info("Received message: {}", message);
    }

}
