package com.app.ch08.concurrency.app01.creation;

public class MySecondThread extends Thread {


    public void run(){
        for(int i=0;i<100;i++){
            try {
                Thread.sleep(100);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            System.out.println("\t"+i);
        }
        System.out.println("------------Finish--------------");
    }
}
