package com.app.ch08.concurrency.app10.executors.ch02.mycollable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        int n=3;
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new CallableWorker("W1", 3000));

        executorService.shutdown();
        System.out.println("------FINISH------");

    }
}
