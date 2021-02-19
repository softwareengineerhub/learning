package com.app;

import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args){
        System.out.println("------------------START SOAP------------------------------------");
        //Endpoint.publish("http://127.0.0.1:8081/calc", new SimpleCalculator());
        Endpoint.publish("http://0.0.0.0:8081/calc", new SimpleCalculator());
        System.out.println("------------------MAIN FINISH------------------------------------");
        /*while(true){
            System.out.println("I am here");
            Thread.sleep(2000);
        }*/
    }
}
