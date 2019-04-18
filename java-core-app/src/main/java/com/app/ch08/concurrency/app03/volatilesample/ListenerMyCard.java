package com.app.ch08.concurrency.app03.volatilesample;

public class ListenerMyCard extends Thread {
    private MyCard myCard;

    public ListenerMyCard(MyCard myCard) {
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
            if(localVal!=myCard.getValue()){
                System.out.println("\tListener: "+localVal);
                localVal=myCard.getValue();
            }
        }
    }
}
