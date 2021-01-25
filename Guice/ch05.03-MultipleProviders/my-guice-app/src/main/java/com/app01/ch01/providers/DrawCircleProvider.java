package com.app01.ch01.providers;

import com.app01.ch01.request.CircleColorValue;
import com.app01.ch01.request.SquareColorValue;
import com.app01.ch01.shape.DrawCircle;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DrawCircleProvider implements Provider<DrawCircle> {

    private String color;

    /*@Inject
    public DrawSquareProvider(String color) {
        this.color = color;
    }*/

    @Inject
    public DrawCircleProvider(@CircleColorValue String color) {
        this.color = color;
    }

    @Override
    public DrawCircle get() {
        return new DrawCircle(color);
    }
}
