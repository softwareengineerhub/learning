package com.app.ch08.concurrency.app10.executors.ch01.myexecutors;

public class Worker implements Runnable {

    @Override
    public void run() {
        System.out.println("START: " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FINISH: " + Thread.currentThread().getName());
    }
}
