package com.app.ch06.io.app03.serial.simple;

import com.app.ch06.io.app03.serial.MyCup;

public interface CupProcessor {

    public void save(MyCup myCup, String filePath);

    public MyCup read(String filePath);
}
