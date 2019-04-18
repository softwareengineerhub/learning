package com.app.ch08.concurrency.app02.badincrement;

public class DecrThread extends Thread {

    private BadIncrement badIncrement;

    public DecrThread(BadIncrement badIncrement) {
        this.badIncrement = badIncrement;
    }

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            badIncrement.decrement();
        }
    }
}
