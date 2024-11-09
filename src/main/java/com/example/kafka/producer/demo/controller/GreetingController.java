package com.example.kafka.producer.demo.controller;

import com.example.kafka.producer.demo.model.Greeting;
import com.example.kafka.producer.demo.producer.KafkaProducerGreeting;
import com.example.kafka.producer.demo.producer.KafkaProducerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private KafkaProducerGreeting kafkaProducerGreeting;

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @PostMapping("/greeting")
    public Greeting newEmployee(@RequestBody Greeting newGreeting) {
        kafkaProducerGreeting.sendMessageWithFuture(newGreeting);
        kafkaProducerMessage.sendMessageWithFuture(newGreeting.toString());
        return newGreeting;
    }
}
