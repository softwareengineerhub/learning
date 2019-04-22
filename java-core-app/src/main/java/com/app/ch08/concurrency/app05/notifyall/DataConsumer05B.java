package com.app.ch08.concurrency.app05.notifyall;

public class DataConsumer05B extends Thread {
    private CommonMonitor monitor;

    public DataConsumer05B(CommonMonitor monitor) {
        this.monitor = monitor;
    }

    public void run(){
        while (true){
            synchronized (monitor){
                while(!monitor.isNeedToConsume()) {
                    try {
                        monitor.wait();
                        System.out.println("\t\tB - got notification");
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println("\tConsumerB");
                return;
            }
        }
    }

}
