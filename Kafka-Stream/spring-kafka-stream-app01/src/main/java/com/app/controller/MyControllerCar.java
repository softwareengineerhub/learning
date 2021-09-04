package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyControllerCar {
    @Autowired
    private KafkaTemplate<String, String> template;

    @GetMapping("car")
    public void send(@RequestParam String model, @RequestParam String make){
        String json = "{\n" +
                "  \"make\": \"%s\",\n" +
                "  \"model\":\"%s\",\n" +
                "  \"color\":\"blue\"\n" +
                "}";
        json = String.format(json, make, model);
        //System.out.println(json);
        template.send("topicCar", json);
    }
}
