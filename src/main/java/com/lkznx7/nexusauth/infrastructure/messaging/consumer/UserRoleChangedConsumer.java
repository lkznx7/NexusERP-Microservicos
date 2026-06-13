package com.lkznx7.nexusauth.infrastructure.messaging.consumer;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class UserRoleChangedConsumer {
    @RabbitListener(queues = "user.role.changed")
    public void consume(Object message) {
    }
}
