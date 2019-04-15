package com.app.ch06.io.app01.fileio;

import java.io.FileNotFoundException;

public interface MyDataProcessor {

    public void saveToFile(String content, String filePath) ;

    public String readFromFile(String filePath);
}
