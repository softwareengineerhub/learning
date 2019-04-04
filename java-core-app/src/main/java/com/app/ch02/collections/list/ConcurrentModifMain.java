package com.app.ch02.collections.list;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentModifMain {

    public static void main(String[] args) {
        List list = new ArrayList();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        for(Object item: list){
            System.out.println(item);
            if("C".equals(item)){
                list.add("QQQ");
            }
        }



    }
}
