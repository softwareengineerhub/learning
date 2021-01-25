package com.app01.ch01;

import com.app01.ch01.module.AppModule;
import com.app01.ch01.request.CircleRequest;
import com.app01.ch01.request.SquareRequest;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainWithGuice {

    private static final String SQUARE_REQ = "SQUARE";
    private static final String CIRCLE_REQ = "CIRCLE";

    public static void main(String[] args) {
        sendRequest(SQUARE_REQ);
        //sendRequest(CIRCLE_REQ);
    }

    private static void sendRequest(String squareReq){
        if(squareReq.equals(SQUARE_REQ)){
            Injector injector = Guice.createInjector(new AppModule());
            SquareRequest request = injector.getInstance(SquareRequest.class);
            request.makeRequest();

            SquareRequest request2 = injector.getInstance(SquareRequest.class);
            System.out.println("request.hashCode()="+request.hashCode());
            System.out.println("request2.hashCode()="+request2.hashCode());
        }

        if(squareReq.equals(CIRCLE_REQ)){
            Injector injector = Guice.createInjector(new AppModule());
            CircleRequest request = injector.getInstance(CircleRequest.class);
            request.makeRequest();
        }


    }
}
