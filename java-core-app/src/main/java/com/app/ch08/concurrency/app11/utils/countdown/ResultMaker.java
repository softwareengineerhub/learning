package com.app.ch08.concurrency.app11.utils.countdown;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class ResultMaker implements Callable {
    private CountDownLatch countDownLatch;

    public ResultMaker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Object call() throws Exception {
        try {
            System.out.println("----------WAITING IN result----------");
            countDownLatch.await();
            //System.out.println("----------FINISH----------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "---FINISH----";
    }
}
