package com.app.ch04.helper;

public class Main {

    public static void main(String[] args) {
        HelperPerson helperPerson = new HelperPerson();
        MyListenerA myListenerA = new MyListenerA();
        MyListenerB myListenerB = new MyListenerB();
        helperPerson.addListener(myListenerA);
        helperPerson.addListener(myListenerB);

        helperPerson.setAge(10);
        helperPerson.setAge(20);
    }
}
