package com.app.ch02.collections.set;

import java.util.HashSet;
import java.util.Set;

public class HashSetMain {

    public static void main(String[] args) {

        Set set = new HashSet();
        Animal a1 = new Animal(1, "A1");
        Animal a2 = new Animal(1, "A1");
        Animal a3 = new Animal(1, "A1");

        set.add(a1);
        set.add(a2);
        set.add(a3);
        System.out.println(set.size());

    }
}
