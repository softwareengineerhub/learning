package com.app.ch08.concurrency.app06.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Shop shop = new Shop(lock, condition);
        User user = new User(lock, condition, shop);

        shop.start();
        user.start();

    }
}
