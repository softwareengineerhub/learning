package com.app01.ch01.request;

import com.app01.ch01.shape.DrawShape;

import javax.inject.Inject;

public class SquareRequestChild extends SquareRequest {
    private DrawShape d;

    @Inject
    public SquareRequestChild(DrawShape d) {
        super(d);
        this.d=d;
    }

    public void makeRequest() {
        System.out.println("child start!!");
        d.draw();
        System.out.println("child finish!!");
    }
}
