package com.app.ch08.concurrency.app13.blockingqueue.delay;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        DelayQueue queue = new DelayQueue();

        Thread producer = new Thread(){

            public void run(){
                for(int i=0;i<10;i++){
                    System.out.println("Produced: "+i);
                    queue.put(new MyDelayData(1000*i));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        Thread consumer = new Thread(){

            public void run(){
                for(;;){
                    try {
                        System.out.println("\tBefore take");
                        Delayed delayed=queue.take();
                        System.out.println("\tconsumed: "+delayed.getDelay(TimeUnit.MILLISECONDS));
                        Thread.sleep(1000);
                        System.out.println(queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        producer.start();
        consumer.start();
    }
}
