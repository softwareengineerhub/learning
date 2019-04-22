package com.app.ch08.concurrency.app04.blockingqueue;

public class Producer extends Thread {
    private int value;
    private boolean produced;
    private Consumer consumer;


    public void run() {
        while (true) {

            synchronized (this) {
                try {
                    while (produced) {
                        this.wait();
                    }
                    value++;
                    System.out.println("Produced: " + value);
                    produced = true;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (consumer) {
                consumer.notify();
            }

        }
    }

    public int consume() {
        produced = false;
        return value;
    }

    public boolean isProduced() {
        return produced;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    /*
    public synchronized void m(){

    }

    public void m2(){
        synchronized (this){

        }
    }
*/

}
