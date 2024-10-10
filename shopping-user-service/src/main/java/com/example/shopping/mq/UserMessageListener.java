package com.example.shopping.mq;

import com.example.shopping.model.UserMq;
import com.example.shopping.service.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = "user-topic", groupId = "userservice")
    public void onUserMessage(ConsumerRecord<String, UserMq> message) {
        System.out.println("Receive user message " + message);
        switch (message.value().type()) {
            case UserMq.USER_UPDATED -> userService.save(message.value().user());
            case UserMq.USER_DELETED -> userService.delete(message.value().user().id());
        }
    }
}
