package com.app.ch08.concurrency.app11.utils.semaphore;

public class MyProcessor {

    public synchronized void doAction(){
        System.out.println("doAction()");
    }
}
