package com.app.ch00.init;

public class SubChild extends Child {

    static {
        System.out.println("\t\tSubChild. static init1()");
    }

    {
        System.out.println("\t\tSubChild. init1()");
    }


    public SubChild() {
        System.out.println("\t\tSubChild()");
    }

    {
        System.out.println("\t\tSubChild. init2()");
    }

    static {
        System.out.println("\t\tSubChild. static init2()");
    }
}
