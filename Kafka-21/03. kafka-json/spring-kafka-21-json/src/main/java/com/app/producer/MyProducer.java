package com.app.producer;

import com.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class MyProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper mapper;

    public void send(Employee employee) {
        System.out.println(String.format("Sending msg: %s", employee));
        String json = mapper.writeValueAsString(employee);
        kafkaTemplate.send("temployee", json);
    }

}
