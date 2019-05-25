package com.app.ch12.patterns.structural.app02.proxy.dynamicproxy;

public class Player implements FootballPalyer, BasketballPlayer {

    @Override
    public void playBasketball() {
        System.out.println("playBasketball()");
    }

    @Override
    public int playFootball() {
        System.out.println("playFootball()");
        return 10;
    }
}
