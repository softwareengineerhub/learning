package com.app01.ch01;

import com.app01.ch01.module.AppModule;
import com.app01.ch01.request.SquareRequest;
import com.app01.ch01.shape.DrawSquare;
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
            //DrawSquare d = injector.getInstance(DrawSquare.class);
            //SquareRequest request = new SquareRequest(d);
            SquareRequest request = injector.getInstance(SquareRequest.class);
            request.makeRequest();

            SquareRequest request2 = injector.getInstance(SquareRequest.class);
            request2.makeRequest();

            System.out.println("request.hashCode="+request.hashCode());
            System.out.println("request2.hashCode="+request2.hashCode());
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
