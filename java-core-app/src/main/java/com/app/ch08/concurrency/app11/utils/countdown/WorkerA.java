package com.app.ch08.concurrency.app11.utils.countdown;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class WorkerA implements Callable {
    private CountDownLatch countDownLatch;
    private String name;

    public WorkerA(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.name=name;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(name+" START!!!");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println(name+" FINISH!!!");
        return null;
    }
}
