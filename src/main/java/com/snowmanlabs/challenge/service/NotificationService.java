package com.snowmanlabs.challenge.service;

import com.snowmanlabs.challenge.service.interfaces.INotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {
    private final RabbitTemplate rabbitTemplate;

    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("books-events", message);
    }
}
