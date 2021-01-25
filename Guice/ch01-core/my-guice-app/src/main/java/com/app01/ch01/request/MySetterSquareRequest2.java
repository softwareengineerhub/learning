package com.app01.ch01.request;

import com.app01.ch01.shape.DrawShape;

import javax.inject.Inject;

public class MySetterSquareRequest2 {
    @Inject
    private DrawShape drawShape;

    public void makeRequest(){
        drawShape.draw();
    }
}
