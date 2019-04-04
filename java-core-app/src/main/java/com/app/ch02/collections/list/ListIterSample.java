package com.app.ch02.collections.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIterSample {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        ListIterator itr = list.listIterator(list.size());
        while(itr.hasPrevious()){
            Object item=itr.previous();
            System.out.println(item);
        }



    }
}
