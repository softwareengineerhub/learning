package com.app.ch08.concurrency.app13.blockingqueue.regular;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerTask implements Runnable {
    private BlockingQueue queue;
    private AtomicInteger value;
    //private long delay

    public ProducerTask(BlockingQueue queue, AtomicInteger value) {
        this.queue = queue;
        this.value=value;
    }

    @Override
    public void run() {
            while(true){
                //System.out.println("here");
                try {

                    int oldValue=value.getAndAdd(1);
                    System.out.println("Producer Before creation: "+oldValue);
                    queue.put(oldValue);
                    //value++;
                    System.out.println("Producer  after put: "+oldValue);

                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
