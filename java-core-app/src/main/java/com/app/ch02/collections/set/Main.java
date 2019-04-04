package com.app.ch02.collections.set;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Set set = new HashSet();
        Set set = new LinkedHashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(set);
        boolean res=set.isEmpty();
        int size = set.size();
        set.clear();
        System.out.println(set);

        set.add("A");
        set.add("B");
        System.out.println(set);
        boolean addRes = set.add("A");
        System.out.println(set);
        System.out.println("addRes="+addRes);

        set.remove("A");
        System.out.println(set);
        set.add("B");
        set.add("c");
        set.add("d");


        Iterator itr = set.iterator();
        while(itr.hasNext()){
            Object item = itr.next();
            System.out.println(item);
        }
        System.out.println("------------");
        for(Object item: set){
            System.out.println(item);
        }

        System.out.println("--------TreeSet-----------------");
        /*Set data = new TreeSet();
        //data.add("B");
        //data.add("w");
        //data.add("c");
        //data.add("u");
        //data.add("11");
        data.add("1");
        System.out.println(data);
        data.add(10);
        System.out.println(data);*/

        //Set data = new TreeSet();
        //data.add(new Object());



    }
}
