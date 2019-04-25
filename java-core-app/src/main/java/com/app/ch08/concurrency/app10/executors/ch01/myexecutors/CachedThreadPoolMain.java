package com.app.ch08.concurrency.app10.executors.ch01.myexecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolMain {

    public static void main(String[] args) {
//        int n = 3;
        ExecutorService executorService = Executors.newCachedThreadPool();


        int taskAmount = 30;
        for (int i = 0; i < taskAmount; i++) {
            executorService.execute(new Worker());
        }



    }

}
