package com.app.ch08.concurrency.app07.timers;

import java.util.Timer;

public class Main {

    public static void main(String[] args) {
        Timer timer = new Timer();
        MyTask myTask = new MyTask("A");
        timer.scheduleAtFixedRate(myTask, 5000, 1000);
        MyTask myTask2 = new MyTask("B");
        timer.scheduleAtFixedRate(myTask2, 0, 500);

        myTask2.cancel();
        myTask.cancel();

        timer.cancel();

    }
}
