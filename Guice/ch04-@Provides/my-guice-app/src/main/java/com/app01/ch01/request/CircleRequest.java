package com.app01.ch01.request;

import com.app01.ch01.shape.DrawShape;

import javax.inject.Inject;
import javax.inject.Named;

public class CircleRequest {

    private DrawShape d;

    @Inject
    public CircleRequest(DrawShape d) {
        this.d=d;
    }



    public void makeRequest(){
        System.out.println("CircleRequest.makeRequest()");
        d.draw();
    }
}
