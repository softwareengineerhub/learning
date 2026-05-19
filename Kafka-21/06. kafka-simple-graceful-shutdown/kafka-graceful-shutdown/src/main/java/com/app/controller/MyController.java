package com.app.controller;

import com.app.producer.MyProducer;
import jakarta.annotation.PreDestroy;
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
        System.out.println("@In Controller");
        String msg = request.getParameter("msg");
        myProducer.send(msg);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("MyController.@PreDestroy.start");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyController.@PreDestroy.finish");
    }
}
