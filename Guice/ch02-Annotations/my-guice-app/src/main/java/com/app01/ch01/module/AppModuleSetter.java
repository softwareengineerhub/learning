package com.app01.ch01.module;

import com.app01.ch01.shape.DrawShape;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.AbstractModule;

public class AppModuleSetter extends AbstractModule {

    public void configure(){
        bind(DrawShape.class).to(DrawSquare.class);

       // bind(MySetterSquareRequest.class).to(MySetterSquareRequest.class);

    }
}
