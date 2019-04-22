package com.app.ch08.concurrency.app08.priority;

public class MyPriorityThread extends Thread {
    private String value;

    public MyPriorityThread(String value, int priority) {
        this.value=value;
        setPriority(priority);
    }

    @Override
    public void run() {
        while(true){
            System.out.println(value);
        }
    }
}
