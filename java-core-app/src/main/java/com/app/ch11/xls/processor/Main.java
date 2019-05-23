package com.app.ch11.xls.processor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath = "bd.xls";
        List<BusinessData> data = initData(10);
        DataProcessor dataProcessor = new DataProcessorImpl();
        dataProcessor.writeToFile(filePath, data);
        List<BusinessData> readData = dataProcessor.readFromFile(filePath);
        for(BusinessData bd: readData){
            System.out.println(bd);
        }
    }

    private static List<BusinessData> initData(int n) {
        List<BusinessData> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            BusinessData bd = new BusinessData();
            bd.setAge(i);
            bd.setName("Name" + i);
            list.add(bd);
        }
        return list;
    }
}
