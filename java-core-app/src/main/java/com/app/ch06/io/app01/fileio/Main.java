package com.app.ch06.io.app01.fileio;

public class Main {

    public static void main(String[] args) {
        MyDataProcessor myDataProcessor = new MyDataProcessorImpl();
        myDataProcessor.saveToFile("Test data21", "test.txt");
        String content = myDataProcessor.readFromFile("test.txt");
        System.out.println(content);
    }
}
