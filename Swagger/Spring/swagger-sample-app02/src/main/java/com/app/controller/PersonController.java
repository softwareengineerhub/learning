package com.app.controller;

import com.app.domain.Person;
import com.app.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private MyRepository myRepository;

    @PostMapping("/person")
    public void create(@Valid @RequestBody Person person){
        myRepository.getList().add(person);
    }

    @GetMapping("/person")
    public List<Person> findAll(){
        return myRepository.getList();
    }
}
