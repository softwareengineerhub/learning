package com.app.ch06.io.app03.serial.bulk;

import com.app.ch06.io.app03.serial.MyCup;

import java.util.List;

public interface BulksOperation {

    public void save(List<MyCup> list, String filePath);

    public List<MyCup> read(String filePath);
}
