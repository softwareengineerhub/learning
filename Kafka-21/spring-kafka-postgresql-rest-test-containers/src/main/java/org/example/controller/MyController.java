package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MyEntity;
import org.example.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @GetMapping
    public MyEntity get(@RequestParam String name, @RequestParam int age) {
        return myService.getData(name, age);
    }
}
