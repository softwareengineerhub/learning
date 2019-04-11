package com.app.ch03.mystack;

import java.util.Arrays;

public class MyStackImpl implements MyStack {
    private Object[] data;
    private int capacity;
    private int n;

    public MyStackImpl(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    public MyStackImpl() {
        this(10);
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }

    @Override
    public void push(Object item) {
        if(n+1>data.length){
            data = Arrays.copyOf(data, data.length+capacity);
        }
        data[n] = item;
        n++;
    }

    @Override
    public Object peek() {
        return data[n-1];
    }

    @Override
    public Object poll() {
        Object value = data[n-1];
        data[n-1] = null;
        n--;
        return value;
    }
}
