package com.app.ch08.concurrency.app08.runnable;

public class Main {

    public static void main(String[] args) {
        Runnable runnable = new MyTaskToProceed();
        Thread t = new Thread(runnable);
        t.start();

        Thread t1 = new Thread(runnable);
        t1.start();

        Thread t2 = new Thread(runnable);
        t2.start();

        Thread t3 = new Thread(runnable);
        t3.start();

        MyThread myThread = new MyThread();
        Thread t4 = new Thread(myThread);
        t4.start();

    }
}
