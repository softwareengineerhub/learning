package com.app.ch08.concurrency.app09.monitor.vs.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample2 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread() {

            public void run() {
                while (true) {
                    while (true) {
                        System.out.println("T1 before lock");
                        try {
                            lock.tryLock();
                            System.out.println("T1 after lock");
                            delay(10000);
                            System.out.println("T1 after delay");
                        } finally {
                            lock.unlock();
                            System.out.println("T1 release lock");
                        }
                    }
                }
            }
        };

        Thread t2 = new Thread() {

            public void run() {
                while (true) {
                    System.out.println("\tT2 before lock");
                    try {
                        if (lock.tryLock(11000, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("\tT2 after lock");
                                delay(5000);
                                System.out.println("\tT2 after delay");
                            } finally {
                                lock.unlock();
                                System.out.println("\tT2 release lock");
                            }
                        }else{
                            System.out.println("OK, going to do other action s");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        t1.start();
        t2.start();
    }

    private static void delay(long delay) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= delay) {
        }
    }

}
