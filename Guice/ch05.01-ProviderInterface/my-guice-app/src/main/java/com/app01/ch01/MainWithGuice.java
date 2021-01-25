package com.app01.ch01;

import com.app01.ch01.module.AppModule;
import com.app01.ch01.request.SquareRequest;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainWithGuice {

    private static final String SQUARE_REQ = "SQUARE";

    public static void main(String[] args) {
        sendRequest(SQUARE_REQ);
    }

    private static void sendRequest(String squareReq){
        if(squareReq.equals(SQUARE_REQ)){
            Injector injector = Guice.createInjector(new AppModule());
            SquareRequest request = injector.getInstance(SquareRequest.class);
            request.makeRequest();
        }


    }
}
