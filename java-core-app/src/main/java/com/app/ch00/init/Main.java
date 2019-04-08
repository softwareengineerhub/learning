package com.app.ch00.init;

public class Main {

    public static void main(String[] args) {
        new SubChild();
       // new SubChild();

        try {
            Class.forName("com.my.app.MyClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
