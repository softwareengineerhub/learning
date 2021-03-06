package com.app01.ch01.providers;

import com.app01.ch01.request.SquareColorValue;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;

public class DrawSquareProvider implements Provider<DrawSquare> {

    private String color;

    /*@Inject
    public DrawSquareProvider(String color) {
        this.color = color;
    }*/

    @Inject
    public DrawSquareProvider(@SquareColorValue String color) {
        this.color = color;
    }

    @Override
    public DrawSquare get() {
        return new DrawSquare(color);
    }
}
