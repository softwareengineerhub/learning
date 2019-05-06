package com.app.ch08.concurrency.app12.threadlocal.safe;

public class MyService {

    private int tmp;

    public void doAction(int delta){
        tmp = delta;
        for(int i=0;i<8000;i++) {
            tmp++;
            tmp--;
        }
        System.out.println(tmp);
    }
}
