package com.app.ch08.concurrency.app02.badincrement;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BadIncrement badIncrement = new BadIncrement();

        Thread a = new IncrThread(badIncrement);
        Thread b = new DecrThread(badIncrement);

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println(badIncrement.getValue());

    }
}
