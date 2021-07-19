package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
public class MyController {

    @GetMapping("/info")
    public String info(){
       // String s = null;
       // s.toString();
        //long noOfDaysBetween = DAYS.between(startDate, endDate);
        return "Hello, "+System.currentTimeMillis()+"; data="+System.getenv("mydata");
    }









}
