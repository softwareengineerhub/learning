package com.app.ch03;

public interface MyList extends Iterable {

    public void add(Object item);

    public Object get(int index);

    public Object set(int index, Object value);

    public void remove(int index);

    public boolean isEmpty();

    public int size();

}
