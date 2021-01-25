package com.app01.ch01.shape;

public class DrawCircle implements DrawShape {

    private String color;

    public DrawCircle(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println("Drawing Circle !; color="+color);
    }
}
