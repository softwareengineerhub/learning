package com.app.ch05.covariant;

public class Main {

    public static void main(String[] args) {
        BusinessProcessor bp = new BusinesProcessorChild();
        A a = bp.processData();
        A a1 = new B();
    }
}
