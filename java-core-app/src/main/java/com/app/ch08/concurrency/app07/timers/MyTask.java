package com.app.ch08.concurrency.app07.timers;

import java.util.TimerTask;

public class MyTask extends TimerTask {
    private String value;

    public MyTask(String value) {
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println(value);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
