package com.app.controller;

import com.app.producer.MyAvroProducer;
import jakarta.servlet.http.HttpServletRequest;
import org.example.avro.MyKey;
import org.example.avro.MyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private MyAvroProducer myAvroProducer;

    @GetMapping("/produce")
    public void produce(HttpServletRequest request) {
        String name = request.getParameter("name");
        var id = System.currentTimeMillis();
        MyKey myKey = new MyKey();
        myKey.setId(id);
        myKey.setTitle("Title" + id);
        MyValue myValue = new MyValue();
        myValue.setAge(10);
        myValue.setName(name);
        myValue.setDescription("DescriptionValue");
        System.out.println("myKey="+myKey);
        System.out.println("myValue="+myValue);
        myAvroProducer.send(myKey, myValue);
    }
}
