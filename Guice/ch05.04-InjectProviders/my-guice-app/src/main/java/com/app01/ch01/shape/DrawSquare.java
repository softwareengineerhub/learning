package com.app01.ch01.shape;

import javax.inject.Inject;

public class DrawSquare implements DrawShape {

    private String color;

    @Inject
    public DrawSquare(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println("Drawing Square !; color="+color);
    }
}
