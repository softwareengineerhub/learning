package com.app.ch02.collections.list;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Object item = new String();
        //ArrayList arrayList = new ArrayList();

        //ArrayList IS-A List
        //List arrayList = new ArrayList();
        List arrayList = new LinkedList();
        System.out.println("-----------add()----------");
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println(arrayList);
        arrayList.add(2,5);
        System.out.println(arrayList);
        //arrayList.add(6,-5);
        System.out.println("-----------get()----------");
        System.out.println(arrayList.get(2));

        System.out.println("-----------set()----------");
        Object oldValue = arrayList.set(0, 10);
        System.out.println("oldValue="+oldValue);
        System.out.println(arrayList);
        System.out.println("-----------remove()----------");
        oldValue = arrayList.remove(3);
        System.out.println("oldValue="+oldValue);
        System.out.println(arrayList);
        System.out.println("-----------size()----------");
        System.out.println(arrayList.size());
        System.out.println("-----------isEmpty()----------");
        System.out.println(arrayList.isEmpty());
        System.out.println("-----------clear()----------");
        arrayList.clear();
        System.out.println(arrayList);
        System.out.println("------------------------");
        List list1 = Arrays.asList(1,2,3,4,5);
        List list2 = new ArrayList();
        for(int i=0;i<list1.size();i++){
            list2.add(i+1);
        }
        System.out.println(list1);
        System.out.println(list2);
        System.out.println("list1.hashCode="+list1.hashCode());
        System.out.println("list2.hashCode="+list2.hashCode());
        System.out.println("list2.equals(list1)="+list2.equals(list1));

        list2.add(10);
        System.out.println("list1.hashCode="+list1.hashCode());
        System.out.println("list2.hashCode="+list2.hashCode());
        System.out.println("list2.equals(list1)="+list2.equals(list1));
        list2 = Arrays.asList(5,2,3,4,1);
        System.out.println("list1.hashCode="+list1.hashCode());
        System.out.println("list2.hashCode="+list2.hashCode());
        System.out.println("list2.equals(list1)="+list2.equals(list1));

        System.out.println("------------ITERATOR-------------------");
        List data = new ArrayList();
        data.add("A");
        data.add("B");
        data.add("C");
        data.add("D");

        Iterator itr = data.iterator();
        while(itr.hasNext()){
            Object item=itr.next();
            System.out.println(item);
        }
        System.out.println("-----------------");
        for(Object item: data){
            System.out.println(item);
        }
        System.out.println("-----------------");
        itr = data.iterator();
//        itr.remove();
        while(itr.hasNext()){
            Object item=itr.next();
            System.out.println(item);
            itr.remove();
        }
        System.out.println(data);


    }
}
