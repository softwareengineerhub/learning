package com.app.ch03.generics.myhashtable;

public class Main {

    public static void main(String[] args) {
        int a = -1234567069;
        System.out.println(Math.abs(a)%289);

        MyHashTable myHashTable = new MyHashTableImpl();

        Object oldValue = myHashTable.put("a","A");
        oldValue = myHashTable.put("b","B");
        oldValue = myHashTable.put("c","C");

        System.out.println(myHashTable.size());

    }
}
