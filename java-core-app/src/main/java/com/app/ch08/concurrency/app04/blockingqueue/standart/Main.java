package com.app.ch08.concurrency.app04.blockingqueue.standart;

import com.app.ch08.concurrency.app04.blockingqueue.Consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Main {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new SynchronousQueue();

        Thread producer = new Thread(){
          int value;

            public void run(){
                while(true){
                    try {
                        Thread.sleep(500);
                        value++;
                        System.out.println("Produced: "+value);
                        blockingQueue.put(value);
                        System.out.println("Producer done!: "+value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };


        Thread consumer = new Consumer(){

            public void run(){
                while(true){
                    try {
                        Thread.sleep(10000);
                        Object res=blockingQueue.take();
                        System.out.println("Consumed: "+res);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        Thread consumer2 = new Consumer(){

            public void run(){
                while(true){
                    try {
                        //Thread.sleep(10000);
                        Object res=blockingQueue.take();
                        System.out.println("Consumed: "+res);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };



        producer.start();
        consumer.start();
        consumer2.start();

    }
}
