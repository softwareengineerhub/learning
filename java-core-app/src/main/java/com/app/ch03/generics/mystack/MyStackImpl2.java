package com.app.ch03.generics.mystack;

import java.util.LinkedList;

public class MyStackImpl2<T> implements MyStack<T> {
    private LinkedList<T> data = new LinkedList();

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(T item) {
        data.add(item);
    }

    @Override
    public T peek() {
        return data.getLast();
    }

    @Override
    public T poll() {
        return data.removeLast();
    }
}
