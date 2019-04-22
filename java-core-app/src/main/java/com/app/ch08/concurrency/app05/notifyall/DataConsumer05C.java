package com.app.ch08.concurrency.app05.notifyall;

public class DataConsumer05C extends Thread {

    private CommonMonitor monitor;

    public DataConsumer05C(CommonMonitor monitor) {
        this.monitor = monitor;
    }

    public void run(){
        while (true){
            synchronized (monitor){
                while(!monitor.isNeedToConsume()) {
                    try {
                        monitor.wait(2000);
                        System.out.println("\t\tC - got notification");
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println("\tConsumerC");
                return;
            }
        }
    }
}
