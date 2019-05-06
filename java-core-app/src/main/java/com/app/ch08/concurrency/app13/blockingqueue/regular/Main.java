package com.app.ch08.concurrency.app13.blockingqueue.regular;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    //int value = 0;
    public static void main(String[] args) {
        AtomicInteger value = new AtomicInteger(0);
        //BlockingQueue queue = new SynchronousQueue();
        BlockingQueue queue = new ArrayBlockingQueue(4);



        ExecutorService producers = Executors.newFixedThreadPool(4);
        ExecutorService consumers = Executors.newFixedThreadPool(2);

        producers.submit(new ProducerTask(queue, value));
        producers.execute(new ProducerTask(queue, value));
        producers.submit(new ProducerTask(queue, value));
        producers.execute(new ProducerTask(queue, value));

        consumers.execute(new ConsumerTask(queue));
        consumers.execute(new ConsumerTask(queue));


    }
}
