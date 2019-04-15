package com.app.ch03.generics.mystack;

public interface MyStack<T> {

    public int size();

    public boolean isEmpty();

    public void push(T item);

    public T peek();

    public T poll();
}
