package com.app01.ch01.providers;

import com.app01.ch01.shape.DrawSquare;
import com.google.inject.Provider;
import com.google.inject.Provides;

public class DrawSquareProvider implements Provider<DrawSquare> {

    @Override
    public DrawSquare get() {
        return new DrawSquare("MyColor");
    }
}
