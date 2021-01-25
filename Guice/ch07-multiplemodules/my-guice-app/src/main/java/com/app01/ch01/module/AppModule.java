package com.app01.ch01.module;

import com.app01.ch01.shape.DrawCircle;
import com.app01.ch01.shape.DrawShape;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class AppModule extends AbstractModule {

    public void configure(){
        Multibinder<DrawShape> drawShapebinder = Multibinder.newSetBinder(binder(), DrawShape.class);
        drawShapebinder.addBinding().to(DrawSquare.class);
        drawShapebinder.addBinding().to(DrawCircle.class);
    }



}
