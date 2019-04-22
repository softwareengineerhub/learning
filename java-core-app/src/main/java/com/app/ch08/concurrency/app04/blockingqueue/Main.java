package com.app.ch08.concurrency.app04.blockingqueue;

public class Main {

    public static void main(String[] args) {
        Consumer c = new Consumer();
        Producer p = new Producer();
        c.setProducer(p);
        p.setConsumer(c);

        p.start();
        c.start();
    }
}
