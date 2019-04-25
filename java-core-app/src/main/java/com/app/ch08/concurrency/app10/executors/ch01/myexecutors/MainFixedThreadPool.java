package com.app.ch08.concurrency.app10.executors.ch01.myexecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFixedThreadPool {

    public static void main(String[] args) {
        int n = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(n);

        int taskAmount = 3;
        for (int i = 0; i < taskAmount; i++) {
            executorService.execute(new Worker());
        }
        executorService.shutdown();
        //executorService.shutdownNow();
        System.out.println("----CONTINUE---");
        for (int i = 0; i < taskAmount; i++) {
            executorService.execute(new Worker());
        }


    }
}
