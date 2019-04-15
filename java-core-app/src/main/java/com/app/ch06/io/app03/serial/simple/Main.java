package com.app.ch06.io.app03.serial.simple;

import com.app.ch06.io.app03.serial.MyCup;

public class Main {

    public static void main(String[] args) {
        String filePath = "cup.txt";
        MyCup myCup = new MyCup();
        myCup.setAge(10);
        myCup.setName("Name10");
        CupProcessor cupProcessor = new CupProcessorImpl();
        //cupProcessor.save(myCup, filePath);
        myCup=cupProcessor.read(filePath);
        System.out.println(myCup);
    }
}
