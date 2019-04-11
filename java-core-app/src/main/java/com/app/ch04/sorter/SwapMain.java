package com.app.ch04.sorter;

public class SwapMain {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;

        a = a+b;
        b = a - b;
        a = a - b;

        System.out.println("a="+a);
        System.out.println("b="+b);

        System.out.println("------------");
        int c = 1;
        int d = 2;
        int tmp = c;
        c = d;
        d= tmp;
        System.out.println("c="+c);
        System.out.println("d="+d);



    }
}
