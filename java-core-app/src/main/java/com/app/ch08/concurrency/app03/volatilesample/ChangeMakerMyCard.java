package com.app.ch08.concurrency.app03.volatilesample;

import java.util.concurrent.atomic.AtomicInteger;

public class ChangeMakerMyCard extends Thread  {
    private MyCard myCard;

    public ChangeMakerMyCard(MyCard myCard) {
        this.myCard = myCard;
    }

    public void run(){
        try {
            Thread.sleep(1000);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        int localVal = myCard.getValue();
        while(localVal<5){
            localVal++;
            System.out.println("Produced: "+localVal);
            myCard.setValue(localVal);
            try {
                Thread.sleep(1500);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
