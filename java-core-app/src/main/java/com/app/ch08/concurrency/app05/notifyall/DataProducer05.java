package com.app.ch08.concurrency.app05.notifyall;

public class DataProducer05 extends Thread {
    private CommonMonitor monitor;

    public DataProducer05(CommonMonitor monitor) {
        this.monitor = monitor;
    }

    public void run(){
            try {
                System.out.println("Producer started");
                Thread.sleep(3000);
                monitor.setNeedToConsume(true);
                System.out.println("Producer made action");
               /* synchronized (monitor) {
                 //   monitor.notifyAll();
                    monitor.notify();
                }*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
