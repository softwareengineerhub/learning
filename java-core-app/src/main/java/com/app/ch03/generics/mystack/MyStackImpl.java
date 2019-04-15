package com.app.ch03.generics.mystack;

import java.util.Arrays;

public class MyStackImpl<T> implements MyStack<T> {
    private T[] data;
    private int capacity;
    private int n;

    public MyStackImpl(int capacity) {
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
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
    public void push(T item) {
        if(n+1>data.length){
            data = Arrays.copyOf(data, data.length+capacity);
        }
        data[n] = item;
        n++;
    }

    @Override
    public T peek() {
        return data[n-1];
    }

    @Override
    public T poll() {
        T value = data[n-1];
        data[n-1] = null;
        n--;
        return value;
    }
}
