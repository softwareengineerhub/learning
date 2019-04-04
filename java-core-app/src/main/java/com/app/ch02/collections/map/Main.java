package com.app.ch02.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("a","A");
        map.put("b","B");
        map.put("c","C");
        map.put("d","D");
        System.out.println(map);
        map.isEmpty();
        int n=map.size();
        System.out.println(n);
        Object oldValue = map.put("c","C2");
        System.out.println("oldValue="+oldValue);
        System.out.println(map);
        System.out.println("---------TreeMap----------");
        Map map2 = new TreeMap();
        map2.put("w","W");
        map2.put("a","A");
        map2.put("e","E");
        System.out.println(map2);
        //map2.put(34,"E");

        Object aValue=map.get("a");
        System.out.println(aValue);

        Set keys = map.keySet();
        for(Object key: keys){
            Object val = map.get(key);
            System.out.println(key+"="+val);
        }

        //map.entrySet();



    }
}
