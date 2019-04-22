package com.app.ch08.concurrency.app08.runnable;

public class MyThread extends Thread {

    public void run(){
        System.out.println("MyThread.run(): "+Thread.currentThread().getName());
    }
}
