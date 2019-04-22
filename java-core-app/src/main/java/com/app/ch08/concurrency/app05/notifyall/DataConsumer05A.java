package com.app.ch08.concurrency.app05.notifyall;

public class DataConsumer05A extends Thread {
    private CommonMonitor monitor;

    public DataConsumer05A(CommonMonitor monitor) {
        this.monitor = monitor;
    }

    public void run(){
        while (true){
            synchronized (monitor){
                while(!monitor.isNeedToConsume()) {
                    try {
                        monitor.wait();
                        System.out.println("\t\tA - got notification");
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                //monitor.notify();
                System.out.println("\tConsumerA");
                return;
            }
        }
    }
}
