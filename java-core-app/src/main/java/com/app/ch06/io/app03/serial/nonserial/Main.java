package com.app.ch06.io.app03.serial.nonserial;

import com.app.ch06.io.app03.serial.advanced.MyAsus;
import com.app.ch06.io.app03.serial.advanced.MyComputer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        String filePath = "garage.txt";
        MyCarData myCarData = new MyCarData();
        myCarData.setName("Name1");
        myCarData.setPrice(1000);

        MyGarage myGarage = new MyGarage();
        myGarage.setCarData(myCarData);
        write(myGarage, filePath);

        System.out.println("-------READ---------");
        Object res = read(filePath);
        System.out.println(res);
    }

    private static void write(Object myComputer, String filePath) throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))){
            out.writeObject(myComputer);
        }
    }

    private static Object read(String filePath) throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))){
            return in.readObject();
        }
    }

}
