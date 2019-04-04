package com.app.ch02.collections.set;

import java.util.Comparator;

public class CatComparator implements Comparator {

    public int compare(Object a, Object b) {
        /*if(a.getClass()==Cat.class  && b.getClass()==Cat.class){
            Cat catA = (Cat) a;
            Cat catB = (Cat) b;
            if(catA.getAge()==catB.getAge()){
                return catA.getName().compareTo(catB.getName());
            }
            return catA.getAge()-catB.getAge();
        }*/
        return 1;
    }
}
