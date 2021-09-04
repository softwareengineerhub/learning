package com.app.controller;

import com.app.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPersonController3 {
    @Autowired
    private KafkaTemplate<String, String> template;
    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping("person3")
    public void send(@RequestParam String name, @RequestParam int age) {
        try {
            Person person = new Person(name, age);
            String text = mapper.writeValueAsString(person);
            template.send("t.commodity.promotion-json-custom2", text);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
