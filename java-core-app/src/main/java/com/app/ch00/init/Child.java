package com.app.ch00.init;

public class Child extends Parent {


    static {
        System.out.println("\tChild. static init1()");
    }

    {
        System.out.println("\tChild. init1()");
    }


    public Child() {
        System.out.println("\tChild()");
    }

    {
        System.out.println("\tChild. init2()");
    }

    static {
        System.out.println("\tChild. static init2()");
    }
}
