package com.app01.ch01.module;

import com.app01.ch01.providers.DrawSquareProvider;
import com.app01.ch01.request.SquareColorValue;
import com.app01.ch01.shape.DrawShape;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {

    public void configure(){
        bind(DrawShape.class).toProvider(DrawSquareProvider.class);
        //bind(DrawShape.class).annotatedWith(Names.named("circle")).to(DrawCircle.class);
    }



}
