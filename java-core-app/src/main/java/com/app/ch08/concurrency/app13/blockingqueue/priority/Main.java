package com.app.ch08.concurrency.app13.blockingqueue.priority;

import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    static int  counter = 0;

    public static void main(String[] args) {
        PriorityBlockingQueue<AppUser> queue = new PriorityBlockingQueue<>(1);

        Thread producer = new Thread(){

            public void run(){
                for(int i=0;i<10;i++) {
                    AppUser appUser = new AppUser();
                    appUser.setAge(counter);
                    appUser.setName("Name" + counter);
                    System.out.println("Before put: " + appUser);
                    queue.put(appUser);
                    System.out.println("After put: " + appUser);
                    counter++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        };
        producer.start();

        Thread consumer = new Thread(){

            public void run(){
                for(;;) {
                    try {
                        Thread.sleep(6000);
                        AppUser appUser = queue.take();
                        System.out.println("\tConsumed: " + appUser);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        consumer.start();
    }
}
