package com.app.ch12.patterns.structural.app02.proxy;

public class Main {

    public static void main(String[] args) {
        CarModel carModel = new Reno();
        String type=carModel.type();
        System.out.println(type);
        System.out.println("------------");
        CarModel carModel2 = new CarProxy(carModel);
        String proxyType=carModel2.type();
        System.out.println(proxyType);
    }
}
