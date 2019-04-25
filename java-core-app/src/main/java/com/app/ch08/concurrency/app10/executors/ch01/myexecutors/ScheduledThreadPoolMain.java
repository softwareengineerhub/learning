package com.app.ch08.concurrency.app10.executors.ch01.myexecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolMain {

    public static void main(String[] args) {
        int n = 3;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(n);


        int taskAmount = 3;
        for (int i = 0; i < taskAmount; i++) {
            executorService.execute(new Worker());
        }

        executorService.scheduleAtFixedRate(new Worker(), 100, 1000, TimeUnit.MILLISECONDS);

    }
}
