package com.app.com.app.ch12.patterns.creational.app01.builder;

public class Main {

    public static void main(String[] args) {
        BusDriver busDriver =
                new BusDriver.BusDriverBuilder().name("A").age(1).build();

    }
}
