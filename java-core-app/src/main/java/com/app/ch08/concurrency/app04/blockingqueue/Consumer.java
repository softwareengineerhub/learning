package com.app.ch08.concurrency.app04.blockingqueue;

public class Consumer extends Thread {
    private Producer producer;

    public void run(){

        while(true){

            synchronized (this){
                try {
                    while (!producer.isProduced()) {
                        this.wait();
                    }
                    System.out.println("\tConsumed: "+producer.consume());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            synchronized (producer){
                producer.notify();
            }
        }
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
