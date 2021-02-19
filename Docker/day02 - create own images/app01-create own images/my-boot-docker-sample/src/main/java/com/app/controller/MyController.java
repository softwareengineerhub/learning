package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RestController
public class MyController {

    @GetMapping("/info")
    public String info(){
       // String s = null;
       // s.toString();
        return "Hello, "+System.currentTimeMillis();
    }



}
