package com.example.kafka.producer.demo.producer;

import com.example.kafka.producer.demo.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerGreeting {

    @Value(value = "${greeting.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Greeting> kafkaTemplate;

    public void sendMessage(Greeting msg) {
        kafkaTemplate.send(topicName, msg);
    }

    public void sendMessageWithFuture(Greeting message) {
        CompletableFuture<SendResult<String, Greeting>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}
