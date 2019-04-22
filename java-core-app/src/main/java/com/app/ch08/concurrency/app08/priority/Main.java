package com.app.ch08.concurrency.app08.priority;

public class Main {

    public static void main(String[] args) {
        Thread a = new MyPriorityThread("A", 1);
        Thread b = new MyPriorityThread("B", 10);
        Thread b1 = new MyPriorityThread("B1", 10);
        Thread b2 = new MyPriorityThread("B2", 10);
        Thread b3 = new MyPriorityThread("B3", 10);


        a.start();
        b.start();
        b1.start();
        b2.start();
        b3.start();
    }
}
