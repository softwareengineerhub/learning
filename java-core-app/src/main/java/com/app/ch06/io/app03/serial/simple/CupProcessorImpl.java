package com.app.ch06.io.app03.serial.simple;

import com.app.ch06.io.app03.serial.MyCup;

import java.io.*;

public class CupProcessorImpl implements CupProcessor {
    @Override
    public void save(MyCup myCup, String filePath) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))){
            out.writeObject(myCup);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public MyCup read(String filePath) {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))){
            return (MyCup) in.readObject();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
}
