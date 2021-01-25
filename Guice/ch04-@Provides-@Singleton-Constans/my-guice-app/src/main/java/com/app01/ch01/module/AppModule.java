package com.app01.ch01.module;

import com.app01.ch01.request.*;
import com.app01.ch01.shape.DrawShape;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {

    public void configure(){
       // bind(DrawShape.class).to(DrawSquare.class);
        //bind(DrawShape.class).annotatedWith(Names.named("circle")).to(DrawCircle.class);
        bind(String.class).annotatedWith(ColorValue.class).toInstance("green");
    }

    /*@Provides
    @Singleton
    public DrawShape providesDrawSquare(){
        return new DrawSquare("Green");
    }*/

    @Provides
    @Singleton
    public DrawShape providesDrawSquare(@ColorValue String color){
        return new DrawSquare(color);
    }
}
