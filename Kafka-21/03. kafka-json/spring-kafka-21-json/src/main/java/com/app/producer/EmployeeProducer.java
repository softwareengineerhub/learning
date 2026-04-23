package com.app.producer;

import com.app.model.Employee;
import com.app.model.EmployeeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducer {

    @Autowired
    private KafkaTemplate<EmployeeKey, Employee> kafkaTemplate;

    public void send(EmployeeKey employeeKey, Employee employee) {
        System.out.println(String.format("EmployeeProducer.Sending key=%s; employee=%s", employeeKey, employee));
        kafkaTemplate.send("temployee", employeeKey, employee);
    }
}
