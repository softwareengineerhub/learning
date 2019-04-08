package com.app.ch00.init;

public class Parent {

    static {
        System.out.println("Parent. static init1()");
    }

    {
        System.out.println("Parent. init1()");
    }


    public Parent() {
        System.out.println("Parent()");
    }

    {
        System.out.println("Parent. init2()");
    }

    static {
        System.out.println("Parent. static init2()");
    }
}
