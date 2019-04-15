package com.app.ch03.generics.myhashtable;

public interface MyHashTable<K,V> {

    public boolean isEmpty();

    public int size();

    public V put(K key, V value);

    public V get(K key);

    public void remove(K key);
}
