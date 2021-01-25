package com.app01.ch01.shape;

public class DrawSquare implements DrawShape {

    private String color;

    public DrawSquare(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println("Drawing Square !; color="+color);
    }
}
