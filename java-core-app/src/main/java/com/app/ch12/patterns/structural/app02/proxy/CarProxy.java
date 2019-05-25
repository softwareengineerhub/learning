package com.app.ch12.patterns.structural.app02.proxy;

public class CarProxy implements CarModel {
    private CarModel target;

    public CarProxy(CarModel target) {
        this.target = target;
    }

    @Override
    public String type() {
        return target.type()+"; addition information: year-->2019";
    }
}
