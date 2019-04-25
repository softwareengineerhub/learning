package com.app.ch08.concurrency.app11.utils.semaphore;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class MyPhilosopher implements Runnable {
    private Semaphore leftSemaphore;
    private Semaphore rightSemaphore;
    private String name;

    public MyPhilosopher(Semaphore leftSemaphore, Semaphore rightSemaphore, String name) {
        this.leftSemaphore = leftSemaphore;
        this.rightSemaphore = rightSemaphore;
        this.name = name;
    }


    @Override
    public void run() {
        while(true) {
            try {
                //System.out.println(name + " Before left");
                leftSemaphore.acquire();
                //System.out.println("\t"+name + " Before right");
                rightSemaphore.acquire();
                System.out.println("\t\t\t\t"+name + " Start eating");
                Thread.sleep(1000);
               // System.out.println("\t\t\t\t"+name + " End eating");
                leftSemaphore.release();
                Thread.sleep(10);
                //System.out.println("\t"+name + " After left");
                rightSemaphore.release();
                Thread.sleep(10);
                //System.out.println(name + " After right");
                //leftSemaphore.release();
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }

    }
}
