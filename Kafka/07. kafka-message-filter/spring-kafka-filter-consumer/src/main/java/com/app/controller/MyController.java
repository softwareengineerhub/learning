package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("produce")
    public void doAction(@RequestParam String msg){
        kafkaTemplate.send("t_topic", msg);
    }
}
