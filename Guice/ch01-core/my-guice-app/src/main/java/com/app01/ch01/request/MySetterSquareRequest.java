package com.app01.ch01.request;

import com.app01.ch01.shape.DrawShape;

import javax.inject.Inject;

public class MySetterSquareRequest {
    private DrawShape drawShape;

    @Inject
    public void setDrawShape(DrawShape drawShape) {
        this.drawShape = drawShape;
    }

    public void makeRequest(){
        drawShape.draw();
    }
}
