package com.app.ch03.myqueue;

public interface MyQueue {

    public int size();

    public boolean isEmpty();

    public void enqueue(Object item);

    public Object dequeue();

}
