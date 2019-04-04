package com.app.ch02.collections.set;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetMain {

    public static void main(String[] args) {
        System.out.println("--------Dog---------");
        Set set = new TreeSet();
        Dog dog1 = new Dog(1,"Dog1");
        Dog dog2 = new Dog(2,"Dog2");
        Dog dog3 = new Dog(3,"Dog3");
        set.add("A");
        set.add(dog1);
        set.add(dog2);
        set.add(dog3);
        System.out.println(set);
        System.out.println("--------Cat---------");
        CatComparator catComparator = new CatComparator();
        Set catSet = new TreeSet(catComparator);

        Cat cat1 = new Cat(1, "C1");
        Cat cat2 = new Cat(2, "C2");
        Cat cat3 = new Cat(3, "C3");


        catSet.add(cat1);
        catSet.add(cat2);
        catSet.add(cat3);
        System.out.println(catSet);
        catSet.add(dog1);
        System.out.println(catSet);
        System.out.println(catSet.size());
        System.out.println("-------------------");
        Cat cat4 = new Cat(2, "C2");
        catSet.add(cat4);
        System.out.println(catSet);
        System.out.println(catSet.size());
    }
}
