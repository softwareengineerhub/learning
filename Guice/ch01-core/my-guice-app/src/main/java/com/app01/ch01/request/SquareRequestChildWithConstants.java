package com.app01.ch01.request;

import com.app01.ch01.shape.DrawShape;

import javax.inject.Inject;

public class SquareRequestChildWithConstants extends SquareRequest {
    private DrawShape d;
    private String color;
    private Integer edge;

    @Inject
    public SquareRequestChildWithConstants(DrawShape d, @ColorValue String color, @EdgeValue  Integer edge) {
        super(d);
        this.d=d;
        this.color=color;
        this.edge=edge;
    }

    public void makeRequest() {
        System.out.println("child start!!");
        d.draw();
        System.out.println("child finish!!; color="+color+"; edge="+edge);
    }
}
