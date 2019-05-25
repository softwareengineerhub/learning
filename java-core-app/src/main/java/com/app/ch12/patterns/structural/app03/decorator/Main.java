package com.app.ch12.patterns.structural.app03.decorator;

public class Main {

    public static void main(String[] args) {
        Cameraman cameraman = new DefaultCameraman();
        Cameraman cameraman1 = new BuffereInputStreamCameraman(new DefaultCameraman());
        Cameraman cameraman2 = new FileInputStremCameraman(new DefaultCameraman());
        String res=cameraman.makeFilm();
        System.out.println(res);
        res=cameraman1.makeFilm();
        System.out.println(res);
        res=cameraman2.makeFilm();
        System.out.println(res);
    }
}
