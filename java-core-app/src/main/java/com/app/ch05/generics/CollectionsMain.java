package com.app.ch05.generics;

import com.app.ch05.covariant.A;
import com.app.ch05.covariant.B;
import com.app.ch05.covariant.C;

import java.util.ArrayList;
import java.util.List;

public class CollectionsMain {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new A());
        list.add(new B());
        list.add(new C());
        /*list.add("");
        for(Object item: list){
            A aItem = (A) item;
        }*/

        List<A> list2 = new ArrayList();
        list2.add(new A());
        list2.add(new B());
        //list2.add("");

        A a = list2.get(0);
        for(A item: list2){

        }


        method(list2);
        List<B> list3 = new ArrayList();
        //method(list3);

        method2(list2);
        method2(list3);

        //list2.add("");

        for(A aItem: list2){
            System.out.println(aItem);
        }
    }

    public static void method(List<A> data){

    }

    public static void method2(List data){
        data.add("test");
    }





}
