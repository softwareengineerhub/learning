package com.app.ch08.concurrency.app08.deadlock;

public class MyDeadLockThread extends  Thread {
    private Object monitorA;
    private Object monitorB;

    public MyDeadLockThread(Object monitorA, Object monitorB) {
        this.monitorA = monitorA;
        this.monitorB = monitorB;
    }

    @Override
    public void run() {

        synchronized (monitorA){
            System.out.println("Inside: "+monitorA);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (monitorB){
                System.out.println("\tInside: "+monitorB);
            }
        }
    }
}
