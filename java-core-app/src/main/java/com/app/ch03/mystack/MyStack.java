package com.app.ch03.mystack;

public interface MyStack {

    public int size();

    public boolean isEmpty();

    public void push(Object item);

    public Object peek();

    public Object poll();
}
