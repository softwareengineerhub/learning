package com.app.ch00.params;

public class Main {

    public static void main(String[] args) {
        String name = args[0];
        System.out.println("firstname="+name);
        System.out.println("lastname="+args[1]);
        System.out.println("--------------------");
        String language = System.getProperty("language");
        System.out.println("language="+language);


    }

}
