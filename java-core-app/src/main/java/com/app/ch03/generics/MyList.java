package com.app.ch03.generics;

public interface MyList<T> extends Iterable<T> {

    public void add(T item);

    public T get(int index);

    public T set(int index, T value);

    public void remove(int index);

    public boolean isEmpty();

    public int size();

}
