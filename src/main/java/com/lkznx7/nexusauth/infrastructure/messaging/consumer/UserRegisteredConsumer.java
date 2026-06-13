package com.lkznx7.nexusauth.infrastructure.messaging.consumer;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class UserRegisteredConsumer {
    @RabbitListener(queues = "user.registered")
    public void consume(Object message) {
    }
}
