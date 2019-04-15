package com.app.ch03.generics.myqueue;

public class Main {

    public static void main(String[] args) {
        MyQueue<String> myQueue = new MyQueueImpl();
        MyQueue<Integer> myQueue2 = new MyQueueImpl();
        MyQueue myQueue3 = new MyQueueImpl();

        myQueue.enqueue("test");
        myQueue2.enqueue(123);
        myQueue3.enqueue(123);
        myQueue3.enqueue("test");
    }
}
