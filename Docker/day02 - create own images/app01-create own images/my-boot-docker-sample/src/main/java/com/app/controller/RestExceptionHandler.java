package com.app.controller;

import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler { //extends ResponseEntityExceptionHandler {

        //@ResponseStatus(HttpStatus.NOT_FOUND)
        @ResponseStatus(code=HttpStatus.NOT_FOUND)
        @ExceptionHandler(Exception.class)
        public void handleAllErrors(Exception ex) {
            //Response wrapped = new Response(HttpServletResponse.SC_BAD_REQUEST, "error");
            //wrapped.setMessage("Could not retrieve data");
            System.out.println("Wrapped");
            //return "wrapped";
        }



}
