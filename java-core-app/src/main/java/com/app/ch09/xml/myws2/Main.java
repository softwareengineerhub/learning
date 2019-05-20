package com.app.ch09.xml.myws2;

import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9090/cars", new MyCarProcessor());
    }
}
