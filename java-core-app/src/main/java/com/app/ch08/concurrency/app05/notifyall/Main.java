package com.app.ch08.concurrency.app05.notifyall;

public class Main {

    public static void main(String[] args) {
        CommonMonitor commonMonitor = new CommonMonitor();
        Thread p = new DataProducer05(commonMonitor);
        Thread a = new DataConsumer05A(commonMonitor);
        Thread b = new DataConsumer05B(commonMonitor);
        Thread c = new DataConsumer05C(commonMonitor);

        p.start();
        a.start();
        b.start();
        c.start();
    }
}
