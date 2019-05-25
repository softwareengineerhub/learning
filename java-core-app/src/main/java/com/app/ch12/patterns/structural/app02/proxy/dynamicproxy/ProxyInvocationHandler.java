package com.app.ch12.patterns.structural.app02.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {
    private Player target;

    public ProxyInvocationHandler(Player target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //System.out.println("method:"+method);
        //System.out.println("args:"+ Arrays.toString(args));
        Object res=method.invoke(target, args);
        //System.out.println(res);
        if("10".equals(res.toString())){
           return 20;
        }
        return res;
    }
}
