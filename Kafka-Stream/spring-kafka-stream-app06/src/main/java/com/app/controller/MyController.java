package com.app.controller;

import com.app.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private KafkaTemplate<String, String> template;
    private ObjectMapper mapper = new ObjectMapper();

    //http://127.0.0.1:8080/produce?msg=TEST
    @GetMapping("produce")
    public void send(@RequestParam String msg) {
        try {
            Person p = new Person();
            p.setName(msg);
            p.setCreditCard("111111");
            p.setAge(123);
            template.send("t.commodity.person2", mapper.writeValueAsString(p));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
