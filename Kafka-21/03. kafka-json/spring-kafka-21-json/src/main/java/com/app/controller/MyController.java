package com.app.controller;

import com.app.consumer.MyJsonEmployeeConsumer;
import com.app.model.Employee;
import com.app.model.EmployeeKey;
import com.app.producer.EmployeeProducer;
import com.app.producer.MyProducer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class MyController {
    @Autowired
    private MyProducer myProducer;
    @Autowired
    private EmployeeProducer employeeProducer;

    @GetMapping("/produce")
    public void produce(HttpServletRequest request) {
        String name = request.getParameter("name");
        EmployeeKey employeeKey = new EmployeeKey(1, name);
        Employee employee = new Employee(UUID.randomUUID().toString(), name, LocalDate.now());
        employeeProducer.send(employeeKey, employee);
    }
}
