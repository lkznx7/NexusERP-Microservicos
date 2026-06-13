package com.lkznx7.nexusauth.infrastructure.messaging.consumer;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class UserLockedConsumer {
    @RabbitListener(queues = "user.locked")
    public void consume(Object message) {
    }
}
