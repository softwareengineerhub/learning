package com.app01.ch01;

import com.app01.ch01.request.SquareRequest;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    private static final String SQUARE_REQ = "SQUARE";

    public static void main(String[] args) {
        sendRequest(SQUARE_REQ);
    }

    private static void sendRequest(String squareReq){
        if(squareReq.equals(SQUARE_REQ)){
            DrawSquare d = new DrawSquare();
            SquareRequest request = new SquareRequest(d);
            request.makeRequest();
        }
    }

}
