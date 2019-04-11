package com.app.ch03.myhashtable;

public interface MyHashTable {

    public boolean isEmpty();

    public int size();

    public Object put(Object key, Object value);

    public Object get(Object key);

    public void remove(Object key);
}
