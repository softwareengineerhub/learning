package com.app.controller;

import com.app.consumer.MyConsumerManager;
import com.app.producer.MyProducer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private MyProducer myProducer;
    @Autowired
    private MyConsumerManager myConsumerManager;

    @GetMapping("/produce")
    public void produce(HttpServletRequest request) {
        String name = request.getParameter("name");
        myProducer.send(name);
    }

    @GetMapping("/pause")
    public void pauseConsumer() {
        myConsumerManager.pause();
    }

    @GetMapping("/resume")
    public void resumeConsumer() {
        myConsumerManager.resume();
    }
}
