package com.app.ch08.concurrency.app12.threadlocal.tl.example;

public class Main {

    public static void main(String[] args) {
        MyBadIncrementSample sample = new MyBadIncrementSample();
        int n = 1000;
        Thread[] threads = new Thread[100];
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(){

                public void run(){
                    for(int i=0;i<n;i++) {
                        sample.increment();
                    }
                    for(int i=0;i<n;i++) {
                        sample.decrement();
                    }
                    System.out.println(sample.getValue());
                }

            };
        }
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }


    }
}
