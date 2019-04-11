package com.app.ch03.myhashtable;

import java.util.LinkedList;

public class MyHashTableImpl implements MyHashTable {
    private LinkedList<Entry>[] backets;
    private int capacity;
    private int n;

    public MyHashTableImpl(int capacity) {
        this.capacity = capacity;
        backets = new LinkedList[capacity];
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
        LinkedList<Entry> backet = backets[index];
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
    public Object get(Object key) {
        int index = Math.abs(key.hashCode()) % backets.length;
        LinkedList<Entry> backet = backets[index];
        for(Entry entry: backet){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public void remove(Object key) {
        int index = Math.abs(key.hashCode()) % backets.length;
        LinkedList<Entry> backet = backets[index];
        for(Entry entry: backet){
            if(entry.key.equals(key)){
                backet.remove(entry);
                n--;
                return;
            }
        }
    }

    public class Entry{
        private Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

}
