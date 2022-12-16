package org.microservices.notificaiton.listener;

import org.microservices.notificaiton.domain.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "notification", groupId = "groupId")
    public void listener(Notification notification)  {
        System.out.println(notification.getProductName());
        System.out.println(notification.getMessage());
        System.out.println(notification.getPrice());
        System.out.println(notification.getLocalDateTime());
    }
}
