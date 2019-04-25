package com.app.ch08.concurrency.app09.monitor.vs.lock;

public class Main {
    private Object monitor = new Object();

    public static void main(String[] args) {
        Main main = new Main();
        Thread t1A = new Thread(){

            public void run(){
                main.m5();
            }
        };

        Thread t1B = new Thread(){

            public void run(){

                main.m6();
            }
        };
        t1A.start();
        t1B.start();

    }


    public synchronized void m1(){
        delay(3000);
        System.out.println("m1: "+Thread.currentThread());
    }

    public void m2(){
        delay(3000);
        System.out.println("m2: "+Thread.currentThread());
    }

    public void m3(){
        System.out.println("Before access to synch block:"+Thread.currentThread());
        synchronized (this) {
            delay(3000);
            System.out.println("m1: " + Thread.currentThread());
        }
    }

    public void m4(){
        synchronized (monitor) {
            delay(3000);
            System.out.println("m1: " + Thread.currentThread());
        }
    }


    public void m5(){
        synchronized (monitor) {
            monitor = new Object();
            System.out.println("m5: start sleep");
            delay(8000);
            System.out.println("m5: " + Thread.currentThread());
        }
    }

    public void m6(){
        delay(1500);
        synchronized (monitor) {
            System.out.println("m6: " + Thread.currentThread());
            monitor = new Object();
            delay(1000);
            System.out.println("m6: " + Thread.currentThread());
        }
    }

    private void delay(long delay){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()-start<=delay){
        }
    }


}
