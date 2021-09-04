package com.app01.javaintroduction.comparator;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<MyCData> list = new ArrayList();
        list.add(new MyCData(1,"Name1", 3));
        list.add(new MyCData(2,"Name2", 1));
        list.add(new MyCData(3,"Name3", 5));
        list.add(new MyCData(4,"Name4", 2));
        list.add(new MyCData(5,"Name5", 4));

        list.sort(new java.util.Comparator<MyCData>() {
            @Override
            public int compare(MyCData o1, MyCData o2) {
                return 0;
            }
        });

        list.sort((a,b)->{
            return -(a.getValue()-b.getValue());
        });
        for(MyCData item: list){
            System.out.println(item);
        }

        System.out.println("------");
        Set set = new HashSet();
        set.add("Samara");
        set.add("Samiha");
        System.out.println(set);
    }

}
