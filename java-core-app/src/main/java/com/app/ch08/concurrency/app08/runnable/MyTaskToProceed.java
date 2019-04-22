package com.app.ch08.concurrency.app08.runnable;

public class MyTaskToProceed implements Runnable {

    @Override
    public void run() {
        System.out.println("MyTaskToProceed.run(): "+Thread.currentThread().getName());
    }
}
