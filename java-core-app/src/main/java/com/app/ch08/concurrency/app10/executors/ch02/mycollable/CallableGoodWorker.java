package com.app.ch08.concurrency.app10.executors.ch02.mycollable;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableGoodWorker implements Callable<Integer> {

    private String name;
    private long delay;
    private Random random = new Random();

    public CallableGoodWorker(String name, long delay) {
        this.name = name;
        this.delay = delay;
    }

    @Override
    public Integer call() {
        System.out.println("@BEGIN: "+name);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("@END: "+name);
        int money = random.nextInt(1000);
        System.out.println("@MONEY: "+money);
        return money;
    }
}
