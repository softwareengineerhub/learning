package com.app01.ch01.request;

import com.app01.ch01.shape.DrawShape;
import com.app01.ch01.shape.DrawSquare;
import com.google.inject.Provider;

import javax.inject.Inject;
import javax.inject.Named;

public class SquareRequest {

   // private DrawShape d;

    private Provider<DrawSquare> drawSquareProvider;

    @Inject
    public SquareRequest(Provider<DrawSquare> drawSquareProvider) {
        this.drawSquareProvider = drawSquareProvider;
    }

    public void makeRequest(){
        System.out.println("SquareRequest.makeRequest()");
        drawSquareProvider.get().draw();
        System.out.println("Call 2nd time");
        drawSquareProvider.get().draw();
    }
}
