package com.app.ch08.concurrency.app11.utils.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
        Semaphore s3 = new Semaphore(1);
        Semaphore s4 = new Semaphore(1);
        Semaphore s5 = new Semaphore(1);
        Semaphore s6 = new Semaphore(1);

        MyPhilosopher f1 = new MyPhilosopher(s1, s6, "F1");
        MyPhilosopher f2 = new MyPhilosopher(s2, s1, "F2");
        MyPhilosopher f3 = new MyPhilosopher(s3, s2, "F3");

        MyPhilosopher f4 = new MyPhilosopher(s4, s3, "F4");
        MyPhilosopher f5 = new MyPhilosopher(s5, s4, "F5");
        MyPhilosopher f6 = new MyPhilosopher(s6, s5, "F6");
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        executorService.submit(f1);
        executorService.submit(f2);
        executorService.submit(f3);
        executorService.submit(f4);
        executorService.submit(f5);
        executorService.submit(f6);



        /*Semaphore semaphore = new Semaphore(2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new MyPhilosopher(semaphore, "A"));
        executorService.submit(new MyPhilosopher(semaphore, "B"));*/

    }



}

