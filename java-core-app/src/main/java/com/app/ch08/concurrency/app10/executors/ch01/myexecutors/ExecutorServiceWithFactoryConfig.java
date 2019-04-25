package com.app.ch08.concurrency.app10.executors.ch01.myexecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExecutorServiceWithFactoryConfig {

    public static void main(String[] args) {
        int n = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(n, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setPriority(1);
                return t;
            }
        });

        int taskAmount = 3;
        for (int i = 0; i < taskAmount; i++) {
            executorService.execute(new Worker());
        }
        executorService.shutdown();

    }

}
