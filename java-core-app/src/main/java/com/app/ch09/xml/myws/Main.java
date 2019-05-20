package com.app.ch09.xml.myws;

import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8089/calc", new MyCalculator());
    }
}
