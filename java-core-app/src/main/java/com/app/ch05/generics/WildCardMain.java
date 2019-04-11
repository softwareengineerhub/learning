package com.app.ch05.generics;

import com.app.ch05.covariant.A;
import com.app.ch05.covariant.B;
import com.app.ch05.covariant.C;

import java.util.ArrayList;
import java.util.List;

public class WildCardMain {

    public static void main(String[] args) {
        List<A> list = new ArrayList();
        //List<A> list2 = new ArrayList<>();
        //List<A> list3 = new ArrayList<A>();
        WildCardMain wildCardMain = new WildCardMain();
        wildCardMain.method(list);
        wildCardMain.method2(list);
        wildCardMain.method3(list);

        List<B> list2 = new ArrayList<>();
        wildCardMain.method3(list2);

    }

    public void method(List<A> data){
        A a = data.get(0);
        data.add(new A());
    }

    public void method2(List<? super A> data){
        Object item = data.get(0);
        //A a = data.get(0);
        data.add(new A());
        //data.add("");
        data.add(new B());
        data.add(new C());
    }

    public void method3(List<? extends A> data){
        /*data.add(new A());
        data.add(new B());
        data.add(new C());*/
        //data.add(new Object());
        //data.add(null);
        A a = data.get(0);
    }

}

