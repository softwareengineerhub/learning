package com.app.ch09.xml.myws;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MyCalculator {

    @WebMethod
    public int add(int a, int b){
        return a+b;
    }

}
