package com.app.ch08.concurrency.app11.utils.countdown;

import java.util.concurrent.*;

public class WithExecutorsMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        int n = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        executorService.submit(new WorkerA(countDownLatch, "W1"));
        executorService.submit(new WorkerA(countDownLatch, "W2"));
        Future<String> futureResult = executorService.submit(new ResultMaker(countDownLatch));
        executorService.shutdown();
        String res = futureResult.get();
        System.out.println(res);
    }
}
