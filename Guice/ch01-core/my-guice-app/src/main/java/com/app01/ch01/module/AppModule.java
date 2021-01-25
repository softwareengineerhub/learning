package com.app01.ch01.module;

import com.app01.ch01.request.*;
import com.app01.ch01.shape.DrawShape;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.AbstractModule;
import com.google.inject.Scope.*;
import com.google.inject.Scopes;

public class AppModule extends AbstractModule {

    public void configure(){
        bind(DrawShape.class).to(DrawSquare.class);
        //bind(SquareRequest.class).to(SquareRequestChild.class);
        //bind(SquareRequest.class).to(SquareRequestChildWithConstants.class);
        //bind(String.class).toInstance("Red");
        //bind(Integer.class).toInstance(10);

       bind(SquareRequest.class).to(SquareRequestChildWithConstants.class).in(Scopes.SINGLETON);
        bind(String.class).annotatedWith(ColorValue.class).toInstance("Red");
        bind(Integer.class).annotatedWith(EdgeValue.class).toInstance(10);


    }
}
