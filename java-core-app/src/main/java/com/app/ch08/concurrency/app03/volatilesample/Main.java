package com.app.ch08.concurrency.app03.volatilesample;

public class Main {

    public static void main(String[] args) {
        MyCard myCard = new MyCard();
        Thread changeMaker = new ChangeMakerMyCard(myCard);
        Thread listener = new ListenerMyCard(myCard);

        listener.start();
        changeMaker.start();

    }
}
