package com.app.ch06.io.app03.serial.advanced;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String filePath = "extension.txt";
        MyAsus myComputer = new MyAsus();
        myComputer.setManufactor("ASUS");
        myComputer.setModelName("Model1");
        myComputer.setPrice(1000);
        write(myComputer, filePath);
        System.out.println("-------READ---------");
        MyComputer res = read(filePath);
        System.out.println(res);

    }



    private static void write(MyComputer myComputer, String filePath) throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))){
            out.writeObject(myComputer);
        }
    }

    private static MyComputer read(String filePath) throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))){
            return (MyComputer) in.readObject();
        }
    }
}
