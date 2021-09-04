package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private KafkaTemplate<String, String> template;

    //http://127.0.0.1:8080/produce?msg=TEST
    @GetMapping("produce")
    public void send(@RequestParam String msg){
        template.send("t.commodity.promotion", msg);
    }
}
