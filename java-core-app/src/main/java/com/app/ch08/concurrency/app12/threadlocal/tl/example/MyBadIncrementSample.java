package com.app.ch08.concurrency.app12.threadlocal.tl.example;

import java.util.function.Supplier;

public class MyBadIncrementSample {

    //private int value;

    private ThreadLocal<Integer> value = ThreadLocal.withInitial(new Supplier<Integer>() {
        @Override
        public Integer get() {
            return 0;
        }
    });

    public void increment() {
        value.set(value.get()+1);
    }

    public void decrement() {
        value.set(value.get()-1);
    }

    public int getValue() {
        //System.out.println(value.hashCode());
        return value.get();
    }
}
