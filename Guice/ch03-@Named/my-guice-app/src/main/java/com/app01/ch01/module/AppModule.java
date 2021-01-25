package com.app01.ch01.module;

import com.app01.ch01.request.*;
import com.app01.ch01.shape.DrawCircle;
import com.app01.ch01.shape.DrawShape;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.AbstractModule;
import com.google.inject.Scope.*;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class AppModule extends AbstractModule {

    public void configure(){
        bind(DrawShape.class).to(DrawSquare.class);
        bind(DrawShape.class).annotatedWith(Names.named("circle")).to(DrawCircle.class);
    }
}
