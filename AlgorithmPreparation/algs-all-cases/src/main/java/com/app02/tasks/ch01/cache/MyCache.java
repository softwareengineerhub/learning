package com.app02.tasks.ch01.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MyCache {

    private Map<String, Object> cache;
    private LinkedList<String> keys;
    private int capacity;

    public MyCache() {
        this(10);
    }

    public MyCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity=capacity;
    }

    public void put(String key, Object value){
        if(cache.size()<capacity || cache.containsKey(key)){
            cache.put(key, value);
            keys.add(key);
            return;
        }

        cache.put(key, value);
        keys.removeFirst();
        keys.add(key);
    }

    public Object get(String key){
        Object value = cache.get(key);
        if(value!=null){
            keys.remove(key);
            keys.add(key);
        }
        return value;
    }


}
