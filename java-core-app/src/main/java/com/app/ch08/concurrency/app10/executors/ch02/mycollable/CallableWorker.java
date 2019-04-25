package com.app.ch08.concurrency.app10.executors.ch02.mycollable;

import java.util.concurrent.Callable;

public class CallableWorker implements Callable {
    private String name;
    private long delay;

    public CallableWorker(String name, long delay) {
        this.name = name;
        this.delay = delay;
    }

    @Override
    public Object call() {
        System.out.println("@START: "+name);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("@FINISH: "+name);
        return "";
    }
}
