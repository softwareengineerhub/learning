package com.app.ch03.generics.myqueue;

public interface MyQueue<T> {

    public int size();

    public boolean isEmpty();

    public void enqueue(T item);

    public T dequeue();

}
