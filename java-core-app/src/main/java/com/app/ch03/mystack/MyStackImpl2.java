package com.app.ch03.mystack;

import java.util.LinkedList;

public class MyStackImpl2 implements MyStack {
    private LinkedList data = new LinkedList();

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(Object item) {
        data.add(item);
    }

    @Override
    public Object peek() {
        return data.getLast();
    }

    @Override
    public Object poll() {
        return data.removeLast();
    }
}
