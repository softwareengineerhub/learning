package com.app.com.app.ch12.patterns.creational.app03.singleton;

public class MySingleton {
    private static MySingleton ourInstance = new MySingleton();

    public static MySingleton getInstance() {
        return ourInstance;
    }

    private MySingleton() {

    }


}
