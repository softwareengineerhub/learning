package com.app.ch08.concurrency.app13.blockingqueue.regular;

import java.util.concurrent.BlockingQueue;

public class ConsumerTask implements Runnable {

    private BlockingQueue queue;


    public ConsumerTask(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            //System.out.println("here");
            try {
                 //Thread.sleep(2000);
                //value++;
                Thread.sleep(10000);
                Object value = queue.take();
                System.out.println("\tConsumed : "+value);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
