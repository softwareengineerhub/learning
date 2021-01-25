package com.app01.ch01.shape;

import com.app01.ch01.constants.Constants;

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

    @Override
    public String getShapeName() {
        return Constants.SQUARE;
    }
}
