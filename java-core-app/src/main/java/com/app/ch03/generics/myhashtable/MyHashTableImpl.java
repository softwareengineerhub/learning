package com.app.ch03.generics.myhashtable;

import java.util.LinkedList;

public class MyHashTableImpl<K,V> implements MyHashTable<K,V> {
    private LinkedList<Entry<K,V>>[] backets;
    private int capacity;
    private int n;

    public MyHashTableImpl(int capacity) {
        this.capacity = capacity;
        backets = new LinkedList[capacity];
        for(int i=0;i<backets.length;i++){
            backets[i] = new LinkedList<>();
        }
    }

    public MyHashTableImpl(){
      this(10);
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Object put(Object key, Object value) {
        int index = Math.abs(key.hashCode()) % backets.length;
        LinkedList<Entry<K,V>> backet = backets[index];
        for(Entry entry: backet){
            if(entry.key.equals(key)){
                Object oldValue = entry.value;
                entry.value=value;
                entry.key=key;
                return oldValue;
            }
        }
        backet.add(new Entry(key, value));
        n++;
        return null;
    }

    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode()) % backets.length;
        LinkedList<Entry<K,V>> backet = backets[index];
        for(Entry<K,V> entry: backet){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public void remove(Object key) {
        int index = Math.abs(key.hashCode()) % backets.length;
        LinkedList<Entry<K,V>> backet = backets[index];
        for(Entry<K,V> entry: backet){
            if(entry.key.equals(key)){
                backet.remove(entry);
                n--;
                return;
            }
        }
    }

    public class Entry<K,V>{
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
