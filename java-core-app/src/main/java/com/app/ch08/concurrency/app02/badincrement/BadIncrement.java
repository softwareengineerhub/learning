package com.app.ch08.concurrency.app02.badincrement;

public class BadIncrement {
    private int value;

    public synchronized void increment() {
        value++;
    }

    public synchronized void decrement() {
        value--;
    }

    public int getValue() {
        return value;
    }
}
