package com.app.ch08.concurrency.app11.utils.cyclebarier;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("------Barrier Passed--------");
            }
        });

        Thread t1 = new Thread(){

            public void run(){
                System.out.println("T1 started");
                try {
                    Thread.sleep(200);
                    System.out.println("T1 completed");
                    cyclicBarrier.await();
                    System.out.println("T1  after barrier");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        };

        Thread t2 = new Thread(){

            public void run(){
                System.out.println("\tT2 started");
                try {
                    Thread.sleep(10000);
                    System.out.println("\tT2 completed");
                    cyclicBarrier.await();
                    System.out.println("\tT2  after barrier");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        };

        t1.start();
        t2.start();



    }
}
