package com.app.controller;

import com.app.producer.MyProducer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private MyProducer myProducer;

    @GetMapping("/produce")
    public void produce(HttpServletRequest request) {
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        myProducer.send(key, value);
    }
}
