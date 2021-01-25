package com.app01.ch01.module;

import com.app01.ch01.providers.DrawSquareProvider;
import com.app01.ch01.request.SquareColorValue;
import com.app01.ch01.shape.DrawShape;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class AppModule extends AbstractModule {

    public void configure(){
        bind(String.class).annotatedWith(SquareColorValue.class).toInstance("MyColor2");
    }



}
