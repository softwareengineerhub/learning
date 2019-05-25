package com.app.ch12.patterns.structural.app02.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyJavaProxy {

    public static void main(String[] args) {
        Class[] items = {FootballPalyer.class, BasketballPlayer.class};
        //Class[] items = {BasketballPlayer.class};
        Player player = new Player();
        InvocationHandler handler = new ProxyInvocationHandler(player);
        FootballPalyer  proxyPlayer = (FootballPalyer) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), items, handler);
        //proxyPlayer.playBasketball();
        //proxyPlayer.playFootball();
        int res=proxyPlayer.playFootball();

        //int res=player.playFootball();
        System.out.println("res="+res);
    }
}
