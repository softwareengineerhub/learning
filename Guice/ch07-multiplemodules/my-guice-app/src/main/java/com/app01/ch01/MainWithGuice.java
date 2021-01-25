package com.app01.ch01;

import com.app01.ch01.module.AppModule;
import com.app01.ch01.module.AppModule1;
import com.app01.ch01.request.ShapeRequest;
import com.google.inject.Guice;
import com.google.inject.Injector;

import static com.app01.ch01.constants.Constants.CIRCLE;
import static com.app01.ch01.constants.Constants.SQUARE;

public class MainWithGuice {

    public static void main(String[] args) {
        //sendRequest(SQUARE);
        sendRequest(CIRCLE);
    }

    private static void sendRequest(String squareReq){
        if(squareReq.equals(SQUARE)){
            Injector injector = Guice.createInjector(new AppModule(), new AppModule1());
            ShapeRequest request = injector.getInstance(ShapeRequest.class);
            request.makeRequest(SQUARE);
        }

        if(squareReq.equals(CIRCLE)){
            Injector injector = Guice.createInjector(new AppModule(), new AppModule1());
            ShapeRequest request = injector.getInstance(ShapeRequest.class);
            request.makeRequest(CIRCLE);
        }


    }
}
