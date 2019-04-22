package com.app.ch08.concurrency.app04.blockingqueue.failed;

public class DefaultConsumer extends Thread {
    private DefaultProducer defaultProducer;

    public void run(){
        while(true){

        }
    }

    public void setDefaultProducer(DefaultProducer defaultProducer) {
        this.defaultProducer = defaultProducer;
    }
}
