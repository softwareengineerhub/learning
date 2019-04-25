package com.app.ch08.concurrency.app01.creation;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        //myThread.setDaemon(true);

        MySecondThread myThread2 = new MySecondThread();

        //myThread.run();
        //myThread2.run();
        myThread.start();
        myThread.join();

        //myThread2.start();
        System.out.println("MainFixedThreadPool next line");
    }
}
