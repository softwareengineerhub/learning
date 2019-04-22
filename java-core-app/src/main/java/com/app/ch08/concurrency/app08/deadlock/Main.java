package com.app.ch08.concurrency.app08.deadlock;

public class Main {

    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        Thread t1 = new MyDeadLockThread(a,b);
        //Thread t2 = new MyDeadLockThread(b,a);
        Thread t2 = new MyDeadLockThread(a, b);
        t1.start();
        t2.start();
    }
}
