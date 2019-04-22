package com.app.ch08.concurrency.app06.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Shop extends Thread {
    private int value;
    private boolean produced;
    private Lock lock;
    private Condition condition;


    public Shop(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        while (true) {
            /*synchronized (monitor){

            }*/
            try {
                lock.lock();

                while (produced) {
                    condition.await();
                }
                value++;
                System.out.println("Produced: " + value);
                produced = true;
                condition.signal();

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public int consume() {
        produced = false;
        return value;
    }

    public boolean isProduced() {
        return produced;
    }
}
