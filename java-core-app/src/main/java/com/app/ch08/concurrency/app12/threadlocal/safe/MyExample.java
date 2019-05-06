package com.app.ch08.concurrency.app12.threadlocal.safe;

public class MyExample {

    public static void main(String[] args) {
        MyThreadSaveService myService = new MyThreadSaveService();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {

                public void run() {
                    myService.doAction(0);
                }
            };
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

    }
}
