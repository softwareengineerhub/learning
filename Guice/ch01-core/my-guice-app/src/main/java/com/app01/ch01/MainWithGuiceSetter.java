package com.app01.ch01;

import com.app01.ch01.module.AppModule;
import com.app01.ch01.module.AppModuleSetter;
import com.app01.ch01.request.MySetterSquareRequest;
import com.app01.ch01.request.MySetterSquareRequest2;
import com.app01.ch01.request.SquareRequest;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainWithGuiceSetter {

    private static final String SQUARE_REQ = "SQUARE";

    public static void main(String[] args) {
        sendRequest(SQUARE_REQ);
    }

    private static void sendRequest(String squareReq){
        if(squareReq.equals(SQUARE_REQ)){
            Injector injector = Guice.createInjector(new AppModuleSetter());

            MySetterSquareRequest request = injector.getInstance(MySetterSquareRequest.class);
            request.makeRequest();

            MySetterSquareRequest2 request2 = injector.getInstance(MySetterSquareRequest2.class);
            request2.makeRequest();

        }
    }

    /*private static void sendRequest(String squareReq){
        if(squareReq.equals(SQUARE_REQ)){
            Injector injector = Guice.createInjector(new AppModule());
            //DrawSquare d = injector.getInstance(DrawSquare.class);
            //SquareRequest request = new SquareRequest(d);
            SquareRequest request = injector.getInstance(SquareRequest.class);

            request.makeRequest();
        }
    }*/

}
