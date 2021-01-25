package com.app01.ch01.request;

import com.app01.ch01.shape.DrawShape;

import javax.inject.Inject;
import javax.inject.Named;

public class SquareRequest {

    private DrawShape d;

    @Inject
    public SquareRequest(DrawShape d) {
        this.d = d;
    }

    public void makeRequest(){
        System.out.println("SquareRequest.makeRequest()");
        d.draw();
    }
}
