package com.app01.ch01.module;

import com.app01.ch01.shape.DrawCircle;
import com.app01.ch01.shape.DrawShape;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class AppModule1 extends AbstractModule {

    public void configure(){
        bind(String.class).toInstance("MyColor");
    }



}
