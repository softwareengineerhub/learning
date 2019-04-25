package com.app.ch08.concurrency.app11.utils.countdown;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        int n=3;
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(){

            public void run(){
                System.out.println("T1 START!!!");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("T1 FINISH!!!");
            }

        };

        Thread t2 = new Thread(){

            public void run(){
                System.out.println("T2 START!!!");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("T2 FINISH!!!");
            }

        };


        Thread resultThread = new Thread(){

            public void run(){
                try {
                    System.out.println("----------WAITING IN result----------");
                    countDownLatch.await();
                    System.out.println("----------FINISH----------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        t1.start();
        t2.start();
        resultThread.start();

    }
}
