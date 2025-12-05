package com.vishruth.Spring_Kafka.controller;


import com.vishruth.Spring_Kafka.service.kafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private kafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public Object publishMessage(@PathVariable String message){
        try {
            publisher.sendMessageToTopic(message);
            return ResponseEntity.ok("message published successfully..");
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
