package com.app.ch08.concurrency.app06.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class User extends Thread {
    private Lock lock;
    private Condition condition;
    private Shop shop;

    public User(Lock lock, Condition condition, Shop shop) {
        this.lock = lock;
        this.condition = condition;
        this.shop = shop;
    }

    public void run() {
        while (true) {
            try {
                lock.lock();

                while(!shop.isProduced()){
                    condition.await();
                }

                int res=shop.consume();
                System.out.println("\tConsumed: "+res);
                Thread.sleep(3000);
                condition.signal();


            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
